package com.tallerantlr.simple.interprete.ide;

import com.tallerantlr.simple.interprete.SimpleLexer;
import com.tallerantlr.simple.interprete.SimpleParser;
import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ast.ASTNode;
import com.tallerantlr.simple.interprete.ast.ProcedureDef;
import com.tallerantlr.simple.interprete.ast.SemanticError;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import javax.swing.SwingUtilities; // Import añadido
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class InterpreterRunner {

    // --- Claves definidas localmente ---
    public static final String TURTLE_STATE_KEY = "__turtleState";
    public static final String DRAWING_DELEGATE_KEY = "__drawingDelegate";
    public static final String PROCEDURES_KEY = "__procedures"; // Clave para la tabla de procedimientos
    // --- FIN ---

    // ========== NUEVA INTERFAZ DELEGADA ==========
    /**
     * Interfaz para desacoplar los nodos AST del CanvasPanel concreto.
     */
    public interface DrawingDelegate {
        void addLine(double x1, double y1, double x2, double y2, Color color);
    }
    // =============================================

    /**
     * Configura listeners de error.
     */
    private static void setupErrorListeners(SimpleLexer lexer, SimpleParser parser,
                                            Consumer<String> err, SyntaxErrorHandler handler,
                                            boolean abortOnError) {
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                              int line, int charPos, String msg, RecognitionException e) {
                if (err != null) err.accept(String.format("Error léxico en %d:%d - %s", line, charPos, msg));
                if (handler != null) handler.onError(line, charPos, msg);
                if (abortOnError) throw new RuntimeException("Abortado por error léxico");
            }
        });
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                              int line, int charPos, String msg, RecognitionException e) {
                if (err != null) err.accept(String.format("Error de sintaxis en %d:%d - %s", line, charPos, msg));
                if (handler != null) handler.onError(line, charPos, msg);
                if (abortOnError) throw new RuntimeException("Abortado por error de sintaxis");
            }
        });
    }

    // ============================
    //   Ejecutar (CORREGIDO)
    // ============================
 // ========== NUEVO MÉTODO SOLO PARA VERIFICACIÓN ==========
    public static void verifyCodeOnly(String source,
                                      Consumer<String> err,
                                      SyntaxErrorHandler handler) throws IOException {

        // Validación de comentarios (igual que en runFromString)
        try (BufferedReader br = new BufferedReader(new StringReader(source))) {
            String first = br.readLine();
            if (first == null || !first.trim().startsWith("//")) {
                throw new RuntimeException("Error: el código debe iniciar con un comentario en la primera línea.");
            }
        }
        boolean hasAnyComment = false;
        try (BufferedReader br = new BufferedReader(new StringReader(source))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("//")) { hasAnyComment = true; break; }
            }
        }
        if (!hasAnyComment) {
            throw new RuntimeException("Error: debe existir al menos un comentario en el programa.");
        }

        // Configuración ANTLR
        CharStream in = CharStreams.fromString(source);
        SimpleLexer lexer = new SimpleLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);

        // --- IMPORTANTE: Configurar listeners para ABORTAR en error ---
        setupErrorListeners(lexer, parser, err, handler, /*abortOnError*/ true);

        // --- IMPORTANTE: Desactivar ejecución DENTRO del parser ---
        parser.executeOnParse = false;
        // --- Podríamos activar enforceVarDecl si queremos esa validación aquí ---
        parser.enforceVarDecl = true; // O false si prefieres no forzarlo en la verificación

        // Entrypoint: program
        try {
            // --- SOLO PARSEAR ---
            // Llamar a program() ejecutará el parseo y las validaciones semánticas
            // que tengas DENTRO de las acciones de la gramática (como verificar proc duplicados).
            // Si encuentra un error (léxico, sintáctico o una RuntimeException de tus acciones),
            // los listeners configurados con abortOnError=true lanzarán una excepción.
            parser.program();

            // --- VALIDACIÓN DE 'haz' (si enforceVarDecl es true) ---
            if (parser.enforceVarDecl && parser.getHazCount() == 0) {
                 throw new RuntimeException("Error: el programa debe declarar al menos una variable con 'Haz'.");
            }

            // --- NO EJECUTAMOS EL BUCLE 'for (ASTNode node : body)' ---
            // Si llegamos aquí, significa que el parseo fue exitoso y las validaciones
            // básicas (comentarios, haz) pasaron.

        } catch (SemanticError se) {
            // Los errores semánticos específicos lanzados por tus nodos AST NO se detectarán aquí
            // porque no estamos ejecutando los nodos. Solo se detectarán errores lanzados
            // durante el parseo o desde las acciones de la gramática.
            // El listener ya debería haber reportado el error. Relanzamos para detener.
            throw se;
        } catch (RuntimeException re) {
            // Captura errores léxicos/sintácticos (lanzados por los listeners)
            // o RuntimeExceptions de las acciones de la gramática.
            // El listener ya debería haber reportado el error. Relanzamos para detener.
            throw re;
        }
        // Si no hubo excepciones, la verificación básica pasó.
    }
    public static void runFromString(String source,
                                     MiniIDE.CanvasPanel canvas,
                                     TurtleState turtleState,
                                     Consumer<String> out,
                                     Consumer<String> err,
                                     SyntaxErrorHandler handler) throws IOException {

        // Validaciones iniciales (comentarios)
        try (BufferedReader br = new BufferedReader(new StringReader(source))) {
            String first = br.readLine();
            if (first == null || !first.trim().startsWith("//")) {
                throw new RuntimeException("Error: el código debe iniciar con un comentario en la primera línea.");
            }
        }
        boolean hasAnyComment = false;
        try (BufferedReader br = new BufferedReader(new StringReader(source))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("//")) { hasAnyComment = true; break; }
            }
        }
        if (!hasAnyComment) {
            throw new RuntimeException("Error: debe existir al menos un comentario en el programa.");
        }

        // Configuración ANTLR
        CharStream in = CharStreams.fromString(source);
        SimpleLexer lexer = new SimpleLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);

        // Listeners
        setupErrorListeners(lexer, parser, err, handler, /*abortOnError*/ true);

        // Delegado de dibujo
        DrawingDelegate drawingDelegate = new DrawingDelegate() {
            @Override
            public void addLine(double x1, double y1, double x2, double y2, Color color) {
                SwingUtilities.invokeLater(() -> canvas.addLine(x1, y1, x2, y2, color));
            }
        };

        // Entrypoint: program
        try {
            // --- Desactivar ejecución en el parser ---
            parser.executeOnParse = false; // <<<--- CORRECCIÓN IMPORTANTE

            // Parsear para obtener el árbol y las tablas
            SimpleParser.ProgramContext tree = parser.program();

            // Obtener las estructuras del parser
            @SuppressWarnings("unchecked")
            List<ASTNode> body = (List<ASTNode>) tree.body;
            Map<String, Object> globals = parser.globals;

            // Validar 'haz'
            if (parser.enforceVarDecl && parser.getHazCount() == 0) {
                 throw new RuntimeException("Error: el programa debe declarar al menos una variable con 'Haz'.");
            }

            // --- Poner estado y delegado DENTRO del mapa 'globals' ---
            globals.put(TURTLE_STATE_KEY, turtleState);
            globals.put(DRAWING_DELEGATE_KEY, drawingDelegate);
            globals.put(PROCEDURES_KEY, parser.getProcTable());

            // --- Ejecutar los nodos AST pasando solo el mapa (contexto) ---
            if (body != null) {
                for (ASTNode node : body) {
                    if (node != null) {
                        node.execute(globals); // Llamar a la firma original
                    }
                }
            } else {
                 if (out != null) out.accept("Advertencia: El cuerpo del programa está vacío o no se generó correctamente.");
            }

        } catch (SemanticError se) {
             if (err != null) err.accept(se.getMessage());
            if (handler != null) handler.onError(se.getLine(), se.getCharPos(), se.getMessage());
            throw se;
        } catch (RuntimeException re) { // Captura RuntimeException y sus subtipos
            if (err != null) err.accept("Error durante la ejecución: " + re.getMessage());
            if (re instanceof RecognitionException && handler != null) {
                RecognitionException antlrEx = (RecognitionException) re;
                Token token = antlrEx.getOffendingToken();
                if (token != null) {
                   handler.onError(token.getLine(), token.getCharPositionInLine(), re.getMessage());
                }
            }
            throw re;
        } finally {
             // Limpieza opcional
             // globals.remove(TURTLE_STATE_KEY);
             // globals.remove(DRAWING_DELEGATE_KEY);
             // globals.remove(PROCEDURES_KEY);
        }
    }

    // Métodos de Compatibilidad comentados
    /*
    public static void runFromString(String source, Consumer<String> out, Consumer<String> err) throws IOException {
        throw new UnsupportedOperationException("Llamar a runFromString sin CanvasPanel y TurtleState ya no es soportado para dibujo.");
    }
    */

    // Parsear solo (sin cambios)
    public static void parseOnly(String source, ParseTreePanel targetPanel,
                                 Consumer<String> err, SyntaxErrorHandler handler) throws IOException {
        CharStream in = CharStreams.fromString(source);
        SimpleLexer lexer = new SimpleLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);

        parser.executeOnParse = false;
        parser.enforceVarDecl = false;

        setupErrorListeners(lexer, parser, err, handler, /*abortOnError*/ false);

        ParseTree tree = parser.program();
        targetPanel.setTree(parser, tree);
    }

    // Compatibilidad parseOnly (sin cambios)
    public static void parseOnly(String source, ParseTreePanel targetPanel,
                                 Consumer<String> err) throws IOException {
        parseOnly(source, targetPanel, err, null);
    }
}