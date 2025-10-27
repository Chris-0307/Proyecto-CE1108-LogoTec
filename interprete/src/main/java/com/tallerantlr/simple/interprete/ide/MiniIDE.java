package com.tallerantlr.simple.interprete.ide;

// Imports existentes
import com.tallerantlr.simple.interprete.SimpleLexer;
import com.tallerantlr.simple.interprete.SimpleParser;
import com.tallerantlr.simple.interprete.TurtleState; // Import añadido

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.io.PrintStream;

// Imports nuevos para CanvasPanel
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MiniIDE extends JFrame {
    private final JTextArea editor = new JTextArea();
    private final JTextArea console = new JTextArea();
    // Crear la instancia del estado ANTES de crear el canvas
    private final TurtleState initialTurtleState = new TurtleState(); // Campo TurtleState añadido
    private final CanvasPanel canvas = new CanvasPanel(initialTurtleState); // Pasar estado al constructor
    private final ParseTreePanel parsePanel = new ParseTreePanel();

    // Pintores para resaltar (sin cambios)
    private final Highlighter.HighlightPainter LINE_PAINTER =
        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 235, 59, 160)); // amarillo fuerte
    private final Highlighter.HighlightPainter TOKEN_PAINTER =
        new DefaultHighlighter.DefaultHighlightPainter(new Color(244, 67, 54, 200));  // rojo fuerte


    public MiniIDE() {
        super("Logo-lite IDE");

        // Editor (sin cambios)
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        editor.setText("// Demo\nhaz a 3\nprintln(\"hola\")\n"); // Ejemplo inicial

        // Consola (sin cambios)
        console.setEditable(false);
        console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        console.setBackground(new Color(250, 250, 250));
        console.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Botones (sin cambios en su creación y listeners básicos)
        JButton runBtn = new JButton("Ejecutar");
        runBtn.addActionListener(e -> runProgram());

        JButton treeBtn = new JButton("Actualizar árbol");
        treeBtn.addActionListener(e -> updateParseTree());

        JButton irBtn = new JButton("Generar IR");
        irBtn.addActionListener(e -> generateIR());

        JButton exeBtn = new JButton("Generar EXE (LLVM)");
        exeBtn.addActionListener(e -> generateExe());

        // Layout (sin cambios)
        JSplitPane rightMiddle = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                wrapWithTitled(new JScrollPane(editor), "Editor"),
                wrapWithTitled(new JScrollPane(console), "Consola")
        );
        rightMiddle.setResizeWeight(0.7);

        JSplitPane right = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                rightMiddle,
                wrapWithTitled(parsePanel, "Árbol de parseo")
        );
        right.setResizeWeight(0.65);

        JSplitPane main = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                wrapWithTitled(canvas, "Lienzo"), // Canvas ya creado con TurtleState
                right
        );
        main.setResizeWeight(0.35);

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(runBtn);
        topBar.add(treeBtn);
        topBar.add(irBtn);
        topBar.add(exeBtn);

        setLayout(new BorderLayout());
        add(topBar, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
    }

    // wrapWithTitled (sin cambios)
    private static JComponent wrapWithTitled(JComponent c, String title) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(title));
        p.add(c, BorderLayout.CENTER);
        return p;
    }

    // ---------- Resaltado de errores en el editor (sin cambios) ----------
    private void clearEditorHighlights() {
        editor.getHighlighter().removeAllHighlights();
    }

    private void highlightError(int line, int column) {
        // ... (código sin cambios)
        try {
            int lineIdx = Math.max(1, line) - 1;     // a 0-index
            int startOfLine = editor.getLineStartOffset(lineIdx);
            int endOfLine   = editor.getLineEndOffset(lineIdx);

            // Resaltado de línea
            editor.getHighlighter().addHighlight(startOfLine, endOfLine, LINE_PAINTER);

            // Resaltado del "token" (una longitud mínima de 1 char)
            int tokenStart = Math.min(startOfLine + Math.max(0, column), endOfLine - 1);
            int tokenEnd   = Math.min(tokenStart + 1, endOfLine);
            editor.getHighlighter().addHighlight(tokenStart, tokenEnd, TOKEN_PAINTER);

            // Mover caret y desplazar a la vista
            editor.setCaretPosition(tokenStart);
            editor.requestFocusInWindow();
            Rectangle r = editor.modelToView(tokenStart);
            if (r != null) editor.scrollRectToVisible(r);
        } catch (BadLocationException ignored) {}
    }

    private SyntaxErrorHandler uiHighlighterHandler() {
        return (line, col, msg) -> SwingUtilities.invokeLater(() -> highlightError(line, col));
    }

    // ---------- Acciones (generateIR, generateExe, updateParseTree sin cambios)----------
    private void generateIR() {
        // ... (código sin cambios)
        console.setText("");
        try {
            // Parsear sin ejecutar
            CharStream in = CharStreams.fromString(editor.getText());
            SimpleLexer lexer = new SimpleLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);
            parser.executeOnParse = false;
            parser.enforceVarDecl = false;

            SimpleParser.ProgramContext tree = parser.program();

            @SuppressWarnings("unchecked")
            java.util.List<com.tallerantlr.simple.interprete.ast.ASTNode> body =
                    (java.util.List<com.tallerantlr.simple.interprete.ast.ASTNode>) tree.body;

            java.util.Map<String, com.tallerantlr.simple.interprete.ast.ProcedureDef> procTable =
                    parser.getProcTable();

            java.util.Set<String> globals = parser.getGlobalNames();

            com.tallerantlr.simple.interprete.codegen.CodeGenerator cg =
                    new com.tallerantlr.simple.interprete.codegen.CodeGenerator(globals);
            cg.generateProgram(body, procTable);

            com.tallerantlr.simple.interprete.ir.IR.IRModule mod = cg.getModule();
            console.append(mod.toString());

        } catch (Throwable t) {
            console.append("[IR ERR] " + t.getMessage() + "\n");
            t.printStackTrace();
        }
    }

    private void generateExe() {
        // ... (código sin cambios)
        console.setText("");
        try {
            // Parsear -> IR
            CharStream in = CharStreams.fromString(editor.getText());
            SimpleLexer lexer = new SimpleLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);
            parser.executeOnParse = false;
            parser.enforceVarDecl = false;
            SimpleParser.ProgramContext tree = parser.program();

            @SuppressWarnings("unchecked")
            java.util.List<com.tallerantlr.simple.interprete.ast.ASTNode> body =
                    (java.util.List<com.tallerantlr.simple.interprete.ast.ASTNode>) tree.body;

            java.util.Map<String, com.tallerantlr.simple.interprete.ast.ProcedureDef> procTable = parser.getProcTable();
            java.util.Set<String> globals = parser.getGlobalNames();

            com.tallerantlr.simple.interprete.codegen.CodeGenerator cg =
                    new com.tallerantlr.simple.interprete.codegen.CodeGenerator(globals);
            cg.generateProgram(body, procTable);
            com.tallerantlr.simple.interprete.ir.IR.IRModule irmod = cg.getModule();

            // Optimizador de IR
            com.tallerantlr.simple.interprete.optimizer.IROptimizer.run(irmod);

            // Emitir LLVM
            com.tallerantlr.simple.interprete.backend.LLVMEmitter emitter =
                    new com.tallerantlr.simple.interprete.backend.LLVMEmitter(irmod);
            String ll = emitter.emit();

            // Guardar .ll y construir
            java.nio.file.Path outDir = java.nio.file.Paths.get("build");
            java.nio.file.Files.createDirectories(outDir);
            java.nio.file.Path llPath = outDir.resolve("program.ll");
            com.tallerantlr.simple.interprete.backend.LLVMToolchain.writeFile(llPath, ll);

            // runtime.o
            java.nio.file.Path runtimeObj = java.nio.file.Paths.get("runtime", "runtime.o");
            java.nio.file.Path exeOut = outDir.resolve(isWindows()? "program.exe" : "program");

            com.tallerantlr.simple.interprete.backend.LLVMToolchain.buildExecutable(llPath, runtimeObj, exeOut);

            console.append("OK: ejecutable generado en " + exeOut.toAbsolutePath() + "\n");
        } catch (Throwable t) {
            console.append("[LLVM ERR] " + t.getMessage() + "\n");
            t.printStackTrace();
        }
    }

    private boolean isWindows() {
        String os = System.getProperty("os.name","").toLowerCase();
        return os.contains("win");
    }

    private void updateParseTree() {
        // ... (código sin cambios)
         clearEditorHighlights();
        try {
            // parseOnly con handler para resaltar errores pero NO aborta
            InterpreterRunner.parseOnly(
                    editor.getText(),
                    parsePanel,
                    s -> console.append(s + "\n"),
                    uiHighlighterHandler()
            );
        } catch (Exception ex) {
            console.append("[ERR] " + ex.getMessage() + "\n");
        }
    }

    // ---------- MÉTODO runProgram MODIFICADO ----------
    private void runProgram() {
        console.setText("");
        clearEditorHighlights();
        updateParseTree(); // Muestra árbol y posibles errores de parse (sin cambios)

        // Redirigir System.out/err (sin cambios)
        PrintStream originalOut = System.out, originalErr = System.err;
        PrintStream outPs = new PrintStream(new ConsoleOutputStream(console, false));
        PrintStream errPs = new PrintStream(new ConsoleOutputStream(console, true));
        System.setOut(outPs);
        System.setErr(errPs);

        // Resetear el estado de la tortuga y limpiar el canvas ANTES de ejecutar
        initialTurtleState.reset();
        canvas.clearDrawing(); // Limpia líneas previas

        // Evitar congelar la UI: ejecutar en SwingWorker
        new SwingWorker<Void, Void>() {
            @Override protected Void doInBackground() {
                try {
                    // MODIFICACIÓN: Pasar la instancia 'canvas' y 'initialTurtleState' al runner
                    // (La firma de runFromString deberá cambiar en el siguiente paso)
                    InterpreterRunner.runFromString(
                            editor.getText(),
                            canvas, // Pasar el canvas
                            initialTurtleState, // Pasar el estado
                            s -> console.append(s + "\n"), // out (sin cambios)
                            s -> console.append(s + "\n"), // err (sin cambios)
                            uiHighlighterHandler()         // handler (sin cambios)
                    );
                } catch (Throwable t) {
                    t.printStackTrace(); // se mostrará en la consola (redirigida)
                }
                return null;
            }

            @Override protected void done() {
                // Restaurar System.out/err (sin cambios)
                System.setOut(originalOut);
                System.setErr(originalErr);

                // AÑADIR: Repintar el canvas DESPUÉS de que termine la ejecución
                canvas.repaint();
            }
        }.execute();
    }

    // ========== CLASE CanvasPanel REEMPLAZADA ==========
    static class CanvasPanel extends JPanel {

        // --- CORRECCIÓN: Usar 'class' en lugar de 'record' ---
        private static class LineSegment {
            final double x1, y1, x2, y2;
            final Color color;

            LineSegment(double x1, double y1, double x2, double y2, Color color) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
                this.color = color;
            }
        }
        // --- FIN CORRECCIÓN ---

        private final List<LineSegment> segments = new ArrayList<>();
        private TurtleState turtleState;

        CanvasPanel(TurtleState initialState) {
            this.turtleState = Objects.requireNonNull(initialState, "TurtleState no puede ser null");
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(400, 400));
        }

        public void addLine(double x1, double y1, double x2, double y2, Color color) {
            segments.add(new LineSegment(x1, y1, x2, y2, color));
        }

        public void clearDrawing() {
            segments.clear();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Grilla y ejes
            g2.setColor(new Color(240, 240, 240));
            int step = 25;
            for (int x = 0; x < getWidth(); x += step) g2.drawLine(x, 0, x, getHeight());
            for (int y = 0; y < getHeight(); y += step) g2.drawLine(0, y, getWidth(), y);
            g2.setColor(new Color(200, 200, 200));
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            g2.drawLine(centerX, 0, centerX, getHeight());
            g2.drawLine(0, centerY, getWidth(), centerY);

            // Dibujar segmentos
            for (LineSegment segment : segments) {
                // --- CORRECCIÓN: Acceder a campos directamente ---
                int x1Swing = logicalToSwingX(segment.x1);
                int y1Swing = logicalToSwingY(segment.y1);
                int x2Swing = logicalToSwingX(segment.x2);
                int y2Swing = logicalToSwingY(segment.y2);
                g2.setColor(segment.color);
                // --- FIN CORRECCIÓN ---
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawLine(x1Swing, y1Swing, x2Swing, y2Swing);
            }

            // Dibujar tortuga
            if (turtleState != null && turtleState.isVisible()) {
                drawTurtle(g2);
            }

            g2.dispose();
        }

        private int logicalToSwingX(double logicalX) {
            return (int) Math.round(logicalX + getWidth() / 2.0);
        }

        private int logicalToSwingY(double logicalY) {
            return (int) Math.round(-logicalY + getHeight() / 2.0); // Invertir Y
        }

        private void drawTurtle(Graphics2D g2) {
            double size = 10.0;
            int turtleXSwing = logicalToSwingX(turtleState.getX());
            int turtleYSwing = logicalToSwingY(turtleState.getY());
            double angleRad = Math.toRadians(turtleState.getAngle());

            Path2D triangle = new Path2D.Double();
            triangle.moveTo(turtleXSwing + size * Math.cos(angleRad),
                            turtleYSwing - size * Math.sin(angleRad)); // Restar Y
            triangle.lineTo(turtleXSwing + (size * 0.6) * Math.cos(angleRad + Math.toRadians(150)),
                            turtleYSwing - (size * 0.6) * Math.sin(angleRad + Math.toRadians(150)));
            triangle.lineTo(turtleXSwing + (size * 0.6) * Math.cos(angleRad - Math.toRadians(150)),
                            turtleYSwing - (size * 0.6) * Math.sin(angleRad - Math.toRadians(150)));
            triangle.closePath();

            g2.setColor(Color.BLUE);
            g2.fill(triangle);
            g2.setColor(Color.DARK_GRAY);
            g2.draw(triangle);
        }

        public void setTurtleState(TurtleState state) {
             this.turtleState = Objects.requireNonNull(state);
         }
    } // Fin CanvasPanel

    // main (sin cambios)
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new MiniIDE().setVisible(true));
    }
} // Fin MiniIDE