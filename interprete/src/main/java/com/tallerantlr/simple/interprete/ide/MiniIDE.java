package com.tallerantlr.simple.interprete.ide;

// Imports existentes
import com.tallerantlr.simple.interprete.SimpleLexer;
import com.tallerantlr.simple.interprete.SimpleParser;
import com.tallerantlr.simple.interprete.TurtleState;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
// Imports NUEVOS para ejecutar el .exe
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
// Fin imports nuevos

import java.io.PrintStream;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher; // Para parsing con expresiones regulares
import java.util.regex.Pattern; // Para parsing con expresiones regulares


public class MiniIDE extends JFrame {
    private final JTextArea editor = new JTextArea();
    private final JTextArea console = new JTextArea();
    private final TurtleState initialTurtleState = new TurtleState();
    private final CanvasPanel canvas = new CanvasPanel(initialTurtleState);
    private final ParseTreePanel parsePanel = new ParseTreePanel();

    private final Highlighter.HighlightPainter LINE_PAINTER =
        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 235, 59, 160));
    private final Highlighter.HighlightPainter TOKEN_PAINTER =
        new DefaultHighlighter.DefaultHighlightPainter(new Color(244, 67, 54, 200));

    // --- Patrones Regex para parsear la salida del .exe ---
    private static final Pattern PATTERN_AVANZA = Pattern.compile("la tortuga avanzó (\\d+) unidades");
    private static final Pattern PATTERN_RETROCEDE =
    	    Pattern.compile("la tortuga retroced(?:io|ió) (\\d+) unidades");
    private static final Pattern PATTERN_GIRA_DERECHA = Pattern.compile("la tortuga giró a la derecha (\\d+) grados");
    private static final Pattern PATTERN_GIRA_IZQUIERDA = Pattern.compile("la tortuga giró a la izquierda (\\d+) grados");
    private static final Pattern PATTERN_PON_COLOR = Pattern.compile("color=(\\w+)");
    private static final Pattern PATTERN_PON_POS = Pattern.compile("tortuga POS=\\((\\d+),(\\d+)\\)");
    private static final Pattern PATTERN_PON_X = Pattern.compile("tortuga X=(\\d+)");
    private static final Pattern PATTERN_PON_Y = Pattern.compile("tortuga Y=(\\d+)");
    private static final Pattern PATTERN_PON_RUMBO = Pattern.compile("pon rumbo = (\\d+) grados");
    private static final Pattern PATTERN_ESPERA = Pattern.compile("espera (\\d+) ms");
    private static final String CMD_BAJA_LAPIZ = "baja lapiz";
    private static final String CMD_SUBE_LAPIZ = "sube lapiz";
    private static final String CMD_CENTRO = "tortuga al centro";
    private static final String CMD_OCULTA = "oculta tortuga";


    public MiniIDE() {
        super("Logo-lite IDE");
        // Editor
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        editor.setText("// Prueba Completa de Comandos de Dibujo\nhaz lado 80 // Variable requerida\n\n// --- 1. Dibuja un cuadrado ROJO con movimiento relativo ---\n// Testa: poncl, bajalapiz, av, gd\nponcl \"rojo\"\nbajalapiz\nav lado\ngd 90\nav lado\ngd 90\nav lado\ngd 90\nav lado\ngd 90 // Termina en la posición inicial, mirando igual\n\n// --- 2. Moverse sin dibujar ---\n// Testa: subelapiz, gi, re\nsubelapiz\ngi 90    // Gira a la izquierda\nre 100    // Retrocede 100 (sin dibujar)\n\n// --- 3. Dibuja una línea AZUL ---\n// Testa: poncl, bajalapiz, av\nponcl \"azul\"\nbajalapiz\nav 50\n\n// --- 4. Moverse a una esquina con posicionamiento absoluto ---\n// Testa: subelapiz, ponpos\nsubelapiz\nponpos [50 50] // Moverse a la coordenada (50, 50) sin dibujar\n\n// --- 5. Dibujar una \"L\" NEGRA usando ponX y ponY ---\n// Testa: poncl, bajalapiz, ponx, pony\nponcl \"negro\"\nbajalapiz\nponx 100  // Dibuja línea horizontal de (50, 50) a (100, 50)\npony 100  // Dibuja línea vertical de (100, 50) a (100, 100)\n\n// --- 6. Volver al centro y pausar ---\n// Testa: centro, espera\nsubelapiz\ncentro    // Mover la tortuga al centro\nespera 60 // Pausa por 1 segundo (60/60)\n\n// --- 7. Dibujar una línea apuntando hacia arriba ---\n// Testa: ponrumbo, bajalapiz, av\nponrumbo 90 // Apuntar hacia arriba (asumiendo 90=arriba)\nbajalapiz\nav 120\n\n// --- 8. Mover a la posición inicial ---\n// Testa: ot\not // Mover a la esquina superior izquierda\n"); // Ejemplo inicial

        // Consola
        console.setEditable(false);
        console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        console.setBackground(new Color(250, 250, 250));
        console.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Botones
        JButton runBtn = new JButton("Ejecutar");
        runBtn.addActionListener(e -> runProgram());
        JButton treeBtn = new JButton("Actualizar árbol");
        treeBtn.addActionListener(e -> updateParseTree());
        JButton irBtn = new JButton("Generar IR");
        irBtn.addActionListener(e -> generateIR());
        JButton exeBtn = new JButton("Generar EXE (LLVM)");
        exeBtn.addActionListener(e -> generateExe());

        // Layout
        JSplitPane rightMiddle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, wrapWithTitled(new JScrollPane(editor), "Editor"), wrapWithTitled(new JScrollPane(console), "Consola"));
        rightMiddle.setResizeWeight(0.7);
        JSplitPane right = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, rightMiddle, wrapWithTitled(parsePanel, "Árbol de parseo"));
        right.setResizeWeight(0.65);
        JSplitPane main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, wrapWithTitled(canvas, "Lienzo"), right);
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

    // Resaltado de errores (sin cambios)
    private void clearEditorHighlights() { editor.getHighlighter().removeAllHighlights();}
    private void highlightError(int line, int column) {
     try {
            int lineIdx = Math.max(1, line) - 1;
            int startOfLine = editor.getLineStartOffset(lineIdx);
            int endOfLine   = editor.getLineEndOffset(lineIdx);
            editor.getHighlighter().addHighlight(startOfLine, endOfLine, LINE_PAINTER);
            int tokenStart = Math.min(startOfLine + Math.max(0, column), endOfLine - 1);
            int tokenEnd   = Math.min(tokenStart + 1, endOfLine);
            editor.getHighlighter().addHighlight(tokenStart, tokenEnd, TOKEN_PAINTER);
            editor.setCaretPosition(tokenStart);
            editor.requestFocusInWindow();
            Rectangle r = editor.modelToView(tokenStart);
            if (r != null) editor.scrollRectToVisible(r);
        } catch (BadLocationException ignored) {}
    }
    private SyntaxErrorHandler uiHighlighterHandler() { return (line, col, msg) -> SwingUtilities.invokeLater(() -> highlightError(line, col));}

    // Acciones generateIR, generateExe, updateParseTree (sin cambios)
    private void generateIR() {
         console.setText("");
        try {
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
        console.setText("");
        try {
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
            com.tallerantlr.simple.interprete.optimizer.IROptimizer.run(irmod);
            com.tallerantlr.simple.interprete.backend.LLVMEmitter emitter =
                    new com.tallerantlr.simple.interprete.backend.LLVMEmitter(irmod);
            String ll = emitter.emit();
            java.nio.file.Path outDir = java.nio.file.Paths.get("build");
            java.nio.file.Files.createDirectories(outDir);
            java.nio.file.Path llPath = outDir.resolve("program.ll");
            com.tallerantlr.simple.interprete.backend.LLVMToolchain.writeFile(llPath, ll);
            java.nio.file.Path runtimeObj = java.nio.file.Paths.get("runtime", "runtime.o");
            java.nio.file.Path exeOut = outDir.resolve(isWindows()? "program.exe" : "program");
            com.tallerantlr.simple.interprete.backend.LLVMToolchain.buildExecutable(llPath, runtimeObj, exeOut);
            console.append("OK: ejecutable generado en " + exeOut.toAbsolutePath() + "\n");
        } catch (Throwable t) {
            console.append("[LLVM ERR] " + t.getMessage() + "\n");
            t.printStackTrace();
        }
    }
    private boolean isWindows() { return System.getProperty("os.name","").toLowerCase().contains("win");}
    private void updateParseTree() {
        clearEditorHighlights();
        try {
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

    // ========== MÉTODO runProgram COMPLETAMENTE REESCRITO ==========
    private void runProgram() {
        console.setText("");
        clearEditorHighlights();
        initialTurtleState.reset();
        canvas.clearDrawing();

        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                Path exePath = Paths.get("build", isWindows() ? "program.exe" : "program");
                if (!exePath.toFile().exists()) {
                    throw new RuntimeException("Error: No se encontró el archivo ejecutable en " + exePath.toAbsolutePath());
                }
                ProcessBuilder pb = new ProcessBuilder(exePath.toAbsolutePath().toString());
                pb.redirectErrorStream(true);
                Process process = pb.start();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        publish(line);
                    }
                }
                boolean finished = process.waitFor(10, TimeUnit.SECONDS);
                if (!finished) {
                    process.destroy();
                    throw new RuntimeException("Error: La ejecución del .exe excedió el tiempo límite.");
                }
                int exitCode = process.exitValue();
                if (exitCode != 0) {
                     publish("[ERR] El proceso .exe terminó con código de error: " + exitCode);
                } else {
                     publish("[INFO] Ejecución del .exe completada.");
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String line : chunks) {
                    console.append(line + "\n");
                    try {
                        executeCommandFromOutput(line, initialTurtleState, canvas);
                    } catch (Exception e) {
                        console.append("[PARSE ERR] " + e.getMessage() + "\n");
                    }
                }
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (Exception e) {
                    console.append("[EXEC ERR] " + e.getMessage() + "\n");
                    e.printStackTrace(new PrintStream(new ConsoleOutputStream(console, true)));
                } finally {
                    canvas.repaint(); // Repintar UNA VEZ al final (mejor rendimiento)
                }
            }
        }.execute();
    }

    // ========== MÉTODO AUXILIAR para parsear salida y dibujar (CON REPAINT) ==========
    private void executeCommandFromOutput(String line, TurtleState turtleState, CanvasPanel canvas) {
        if (line == null || line.isBlank()) return;
        String trimmedLine = line.trim();
        Matcher matcher;
        // Removimos la depuración System.out.println para limpiar la salida

        if ((matcher = PATTERN_AVANZA.matcher(trimmedLine)).find()) {
            double amount = Double.parseDouble(matcher.group(1));
            double startX = turtleState.getX(), startY = turtleState.getY();
            turtleState.moveForward(amount);
            if (turtleState.isPenDown()) {
                final double endX = turtleState.getX(), endY = turtleState.getY(); final Color color = turtleState.getPenColor();
                SwingUtilities.invokeLater(() -> {
                    canvas.addLine(startX, startY, endX, endY, color);
                    canvas.repaint(); // <<<--- REPAINT AQUÍ
                });
            }
        } else if ((matcher = PATTERN_RETROCEDE.matcher(trimmedLine)).find()) {
            double amount = Double.parseDouble(matcher.group(1));
            double startX = turtleState.getX(), startY = turtleState.getY();
            turtleState.moveForward(-amount);
            if (turtleState.isPenDown()) {
                final double endX = turtleState.getX(), endY = turtleState.getY(); final Color color = turtleState.getPenColor();
                SwingUtilities.invokeLater(() -> {
                    canvas.addLine(startX, startY, endX, endY, color);
                    canvas.repaint(); // <<<--- REPAINT AQUÍ
                });
            }
        } else if ((matcher = PATTERN_GIRA_DERECHA.matcher(trimmedLine)).find()) {
            turtleState.turnRight(Double.parseDouble(matcher.group(1)));
            SwingUtilities.invokeLater(() -> canvas.repaint()); // Repintar para ver cambio de ángulo
        } else if ((matcher = PATTERN_GIRA_IZQUIERDA.matcher(trimmedLine)).find()) {
            turtleState.turnLeft(Double.parseDouble(matcher.group(1)));
            SwingUtilities.invokeLater(() -> canvas.repaint()); // Repintar para ver cambio de ángulo
        } else if ((matcher = PATTERN_PON_COLOR.matcher(trimmedLine)).matches()) {
            String colorName = matcher.group(1).toLowerCase();
            Color color;
            switch (colorName) {
                case "rojo": color = Color.RED; break;
                case "azul": color = Color.BLUE; break;
                case "negro": color = Color.BLACK; break;
                default: color = Color.BLACK; System.err.println("Color desconocido: " + colorName);
            }
            turtleState.setPenColor(color);
        } else if ((matcher = PATTERN_PON_POS.matcher(trimmedLine)).matches()) {
            turtleState.setPosition(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)));
            SwingUtilities.invokeLater(() -> canvas.repaint()); // Repintar para ver cambio de posición
        } else if ((matcher = PATTERN_PON_X.matcher(trimmedLine)).matches()) {
            double x = Double.parseDouble(matcher.group(1));
            double startX = turtleState.getX(), startY = turtleState.getY();
            turtleState.setX(x);
            if (turtleState.isPenDown()) {
                final double endX = turtleState.getX(); final Color color = turtleState.getPenColor();
                SwingUtilities.invokeLater(() -> {
                    canvas.addLine(startX, startY, endX, startY, color);
                    canvas.repaint(); // <<<--- REPAINT AQUÍ
                });
            } else { // Si el lápiz está arriba, solo repintar para ver la nueva posición
                 SwingUtilities.invokeLater(() -> canvas.repaint());
            }
        } else if ((matcher = PATTERN_PON_Y.matcher(trimmedLine)).matches()) {
            double y = Double.parseDouble(matcher.group(1));
            double startX = turtleState.getX(), startY = turtleState.getY();
            turtleState.setY(y);
            if (turtleState.isPenDown()) {
                final double endY = turtleState.getY(); final Color color = turtleState.getPenColor();
                SwingUtilities.invokeLater(() -> {
                    canvas.addLine(startX, startY, startX, endY, color);
                    canvas.repaint(); // <<<--- REPAINT AQUÍ
                });
            } else { // Si el lápiz está arriba, solo repintar para ver la nueva posición
                 SwingUtilities.invokeLater(() -> canvas.repaint());
            }
        } else if ((matcher = PATTERN_PON_RUMBO.matcher(trimmedLine)).matches()) {
            turtleState.setAngle(Double.parseDouble(matcher.group(1)));
             SwingUtilities.invokeLater(() -> canvas.repaint()); // Repintar para ver cambio de ángulo
        } else if ((matcher = PATTERN_ESPERA.matcher(trimmedLine)).matches()) {
            // Ignorado en la simulación
        } else if (trimmedLine.equalsIgnoreCase(CMD_BAJA_LAPIZ)) {
            turtleState.setPenDown(true);
        } else if (trimmedLine.equalsIgnoreCase(CMD_SUBE_LAPIZ)) {
            turtleState.setPenDown(false);
        } else if (trimmedLine.equalsIgnoreCase(CMD_CENTRO)) {
            turtleState.setPosition(0.0, 0.0);
             SwingUtilities.invokeLater(() -> canvas.repaint()); // Repintar para ver cambio de posición
        } else if (trimmedLine.equalsIgnoreCase(CMD_OCULTA)) {
            turtleState.setVisible(false);
            turtleState.goToOriginTopLeft(400, 400); // Usando valores placeholder
             SwingUtilities.invokeLater(() -> canvas.repaint()); // Repintar para ocultar y mover
        }
    }

    // Clase CanvasPanel (sin cambios)
    static class CanvasPanel extends JPanel {
        private static class LineSegment {
             final double x1, y1, x2, y2; final Color color;
             LineSegment(double x1, double y1, double x2, double y2, Color color) {
                this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2; this.color = color;
            }
        }
        private final List<LineSegment> segments = new ArrayList<>();
        private TurtleState turtleState;

        CanvasPanel(TurtleState initialState) {
            this.turtleState = Objects.requireNonNull(initialState, "TurtleState no puede ser null");
            setBackground(Color.WHITE); setPreferredSize(new Dimension(400, 400));
        }
        public void addLine(double x1, double y1, double x2, double y2, Color color) { segments.add(new LineSegment(x1, y1, x2, y2, color));}
        public void clearDrawing() { segments.clear(); repaint();}
        @Override
        protected void paintComponent(Graphics g) {
             super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Grilla y ejes
            g2.setColor(new Color(240, 240, 240)); int step = 25;
            for (int x = 0; x < getWidth(); x += step) g2.drawLine(x, 0, x, getHeight());
            for (int y = 0; y < getHeight(); y += step) g2.drawLine(0, y, getWidth(), y);
            g2.setColor(new Color(200, 200, 200)); int centerX = getWidth() / 2, centerY = getHeight() / 2;
            g2.drawLine(centerX, 0, centerX, getHeight()); g2.drawLine(0, centerY, getWidth(), centerY);
            // Dibujar segmentos
            for (LineSegment segment : segments) {
                int x1Swing = logicalToSwingX(segment.x1); int y1Swing = logicalToSwingY(segment.y1);
                int x2Swing = logicalToSwingX(segment.x2); int y2Swing = logicalToSwingY(segment.y2);
                g2.setColor(segment.color); g2.setStroke(new BasicStroke(1.5f));
                g2.drawLine(x1Swing, y1Swing, x2Swing, y2Swing);
            }
            // Dibujar tortuga
            if (turtleState != null && turtleState.isVisible()) { drawTurtle(g2); }
            g2.dispose();
        }
        private int logicalToSwingX(double logicalX) { return (int) Math.round(logicalX + getWidth() / 2.0);}
        private int logicalToSwingY(double logicalY) { return (int) Math.round(-logicalY + getHeight() / 2.0);}
        private void drawTurtle(Graphics2D g2) {
             double size = 10.0;
            int turtleXSwing = logicalToSwingX(turtleState.getX()); int turtleYSwing = logicalToSwingY(turtleState.getY());
            double angleRad = Math.toRadians(turtleState.getAngle());
            Path2D triangle = new Path2D.Double();
            triangle.moveTo(turtleXSwing + size * Math.cos(angleRad), turtleYSwing - size * Math.sin(angleRad));
            triangle.lineTo(turtleXSwing + (size * 0.6) * Math.cos(angleRad + Math.toRadians(150)), turtleYSwing - (size * 0.6) * Math.sin(angleRad + Math.toRadians(150)));
            triangle.lineTo(turtleXSwing + (size * 0.6) * Math.cos(angleRad - Math.toRadians(150)), turtleYSwing - (size * 0.6) * Math.sin(angleRad - Math.toRadians(150)));
            triangle.closePath();
            g2.setColor(Color.BLUE); g2.fill(triangle);
            g2.setColor(Color.DARK_GRAY); g2.draw(triangle);
        }
        public void setTurtleState(TurtleState state) { this.turtleState = Objects.requireNonNull(state);}
    } // Fin CanvasPanel

    // main (sin cambios)
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new MiniIDE().setVisible(true));
    }
} // Fin MiniIDE