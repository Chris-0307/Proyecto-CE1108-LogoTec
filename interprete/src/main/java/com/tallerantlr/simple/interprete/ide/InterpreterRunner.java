package com.tallerantlr.simple.interprete.ide;

import com.tallerantlr.simple.interprete.SimpleLexer;
import com.tallerantlr.simple.interprete.SimpleParser;
import com.tallerantlr.simple.interprete.ast.SemanticError;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.function.Consumer;

public class InterpreterRunner {

    /**
     * Configura listeners de error para lexer y parser.
     * @param abortOnError si true, lanza RuntimeException al 1er error; si false, reporta pero no aborta.
     */
    private static void setupErrorListeners(SimpleLexer lexer,
                                            SimpleParser parser,
                                            Consumer<String> err,
                                            SyntaxErrorHandler handler,
                                            boolean abortOnError) {
        // Lexer
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                              int line, int charPos, String msg, RecognitionException e) {
                if (err != null) {
                    err.accept(String.format("Error léxico en %d:%d - %s", line, charPos, msg));
                }
                if (handler != null) handler.onError(line, charPos, msg);
                if (abortOnError) throw new RuntimeException("Abortado por error léxico");
            }
        });

        // Parser
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                              int line, int charPos, String msg, RecognitionException e) {
                if (err != null) {
                    err.accept(String.format("Error de sintaxis en %d:%d - %s", line, charPos, msg));
                }
                if (handler != null) handler.onError(line, charPos, msg);
                if (abortOnError) throw new RuntimeException("Abortado por error de sintaxis");
            }
        });
    }

    // ============================
    //   Ejecutar (con handler)
    // ============================
    public static void runFromString(String source,
                                     Consumer<String> out,
                                     Consumer<String> err,
                                     SyntaxErrorHandler handler) throws IOException {
        // Validación: primera línea debe ser comentario
        try (BufferedReader br = new BufferedReader(new StringReader(source))) {
            String first = br.readLine();
            if (first == null || !first.trim().startsWith("//")) {
                throw new RuntimeException("Error: el código debe iniciar con un comentario en la primera línea.");
            }
        }
        // También al menos un comentario en todo el código
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

        CharStream in = CharStreams.fromString(source);
        SimpleLexer lexer = new SimpleLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);

        // Listeners: abortamos en error durante ejecución
        setupErrorListeners(lexer, parser, err, handler, /*abortOnError*/ true);

        // Entrypoint: program (las acciones en 'program' ejecutan el body)
        try {
            parser.program();
        } catch (SemanticError se) {
            // Errores semánticos con posición → reportar y mandar al resaltador
            if (err != null) err.accept(se.getMessage());
            if (handler != null) handler.onError(se.getLine(), se.getCharPos(), se.getMessage());
            throw se; // re-lanzamos para que el flujo de ejecución se detenga
        }
    }

    // Compatibilidad con firma antigua
    public static void runFromString(String source,
                                     Consumer<String> out,
                                     Consumer<String> err) throws IOException {
        runFromString(source, out, err, null);
    }

    // ============================
    //   Parsear solo (con handler)
    // ============================
    public static void parseOnly(String source,
                                 ParseTreePanel targetPanel,
                                 Consumer<String> err,
                                 SyntaxErrorHandler handler) throws IOException {
        // Para el árbol NO exigimos el comentario/ni variable mínima
        CharStream in = CharStreams.fromString(source);
        SimpleLexer lexer = new SimpleLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);

        // No ejecutar acciones ni validar "al menos una var"
        parser.executeOnParse = false;
        parser.enforceVarDecl = false;

        // Listeners: NO abortamos para poder mostrar árbol parcial
        setupErrorListeners(lexer, parser, err, handler, /*abortOnError*/ false);

        ParseTree tree = parser.program();
        targetPanel.setTree(parser, tree);
    }

    // Compatibilidad con firma antigua
    public static void parseOnly(String source,
                                 ParseTreePanel targetPanel,
                                 Consumer<String> err) throws IOException {
        parseOnly(source, targetPanel, err, null);
    }
}
