package com.tallerantlr.simple.interprete.ide;

import com.tallerantlr.simple.interprete.SimpleLexer;
import com.tallerantlr.simple.interprete.SimpleParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.io.PrintStream;


public class MiniIDE extends JFrame {
    private final JTextArea editor = new JTextArea();
    private final JTextArea console = new JTextArea();
    private final CanvasPanel canvas = new CanvasPanel();
    private final ParseTreePanel parsePanel = new ParseTreePanel();

 // Pintores para resaltar (colores más visibles)
    private final Highlighter.HighlightPainter LINE_PAINTER =
        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 235, 59, 160)); // amarillo fuerte
    private final Highlighter.HighlightPainter TOKEN_PAINTER =
        new DefaultHighlighter.DefaultHighlightPainter(new Color(244, 67, 54, 200));  // rojo fuerte


    public MiniIDE() {
        super("Logo-lite IDE");

        // Editor
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        // Texto inicial válido con tu gramática (comentario + al menos un HAZ)
        editor.setText("// Demo\nhaz a 3\nprintln(\"hola\")\n");

        // Consola
        console.setEditable(false);
        console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        console.setBackground(new Color(250, 250, 250));
        console.setBorder(new EmptyBorder(6, 6, 6, 6));
        
        

        // Botón ejecutar
        JButton runBtn = new JButton("Ejecutar");
        runBtn.addActionListener(e -> runProgram());

        JButton treeBtn = new JButton("Actualizar árbol");
        treeBtn.addActionListener(e -> updateParseTree());

        // Panel derecho medio: editor/consola
        JSplitPane rightMiddle = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                wrapWithTitled(new JScrollPane(editor), "Editor"),
                wrapWithTitled(new JScrollPane(console), "Consola")
        );
        rightMiddle.setResizeWeight(0.7);

        // Panel derecho externo: editor+consola vs árbol
        JSplitPane right = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                rightMiddle,
                wrapWithTitled(parsePanel, "Árbol de parseo")
        );
        right.setResizeWeight(0.65);

        // Split principal: lienzo | (editor+consola | árbol)
        JSplitPane main = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                wrapWithTitled(canvas, "Lienzo"),
                right
        );
        main.setResizeWeight(0.35);

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(runBtn);
        topBar.add(treeBtn);
        
     // dentro del constructor MiniIDE:
        JButton irBtn = new JButton("Generar IR");
        irBtn.addActionListener(e -> generateIR());

        topBar.add(irBtn);


        setLayout(new BorderLayout());
        add(topBar, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
    }

    private static JComponent wrapWithTitled(JComponent c, String title) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(title));
        p.add(c, BorderLayout.CENTER);
        return p;
    }

    // ---------- Resaltado de errores en el editor ----------

    private void clearEditorHighlights() {
        editor.getHighlighter().removeAllHighlights();
    }

    /**
     * Resalta línea completa y el token aproximado (a partir de column).
     * line y column son 1-indexados tal como llegan de ANTLR.
     */
    private void highlightError(int line, int column) {
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
        // Recibe (line, col, msg) y resalta en EDT
        return (line, col, msg) -> SwingUtilities.invokeLater(() -> highlightError(line, col));
    }

    // ---------- Acciones ----------
    
    
    private void generateIR() {
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

            // ⛳️ CAMBIO CLAVE: usar el getter en vez de acceder al campo
            java.util.Map<String, com.tallerantlr.simple.interprete.ast.ProcedureDef> procTable =
                    parser.getProcTable();

            com.tallerantlr.simple.interprete.codegen.CodeGenerator cg =
                    new com.tallerantlr.simple.interprete.codegen.CodeGenerator();
            cg.generateProgram(body, procTable);

            com.tallerantlr.simple.interprete.ir.IR.IRModule mod = cg.getModule();
            console.append(mod.toString());

        } catch (Throwable t) {
            console.append("[IR ERR] " + t.getMessage() + "\n");
            t.printStackTrace();
        }
    }



    private void updateParseTree() {
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

    private void runProgram() {
        console.setText("");
        clearEditorHighlights();
        updateParseTree(); // muestra árbol y posibles errores de parse

        // Redirigir System.out/err a la consola solo durante la ejecución
        PrintStream originalOut = System.out, originalErr = System.err;
        PrintStream outPs = new PrintStream(new ConsoleOutputStream(console, false));
        PrintStream errPs = new PrintStream(new ConsoleOutputStream(console, true));
        System.setOut(outPs);
        System.setErr(errPs);

        // Evitar congelar la UI: ejecutar en SwingWorker
        new SwingWorker<Void, Void>() {
            @Override protected Void doInBackground() {
                try {
                    InterpreterRunner.runFromString(
                            editor.getText(),
                            s -> console.append(s + "\n"),
                            s -> console.append(s + "\n"),
                            uiHighlighterHandler() // aquí sí aborta en el primer error
                    );
                    // TODO: cuando implementes la tortuga, actualiza estado y repinta:
                    // canvas.repaint();
                } catch (Throwable t) {
                    t.printStackTrace(); // se mostrará en la consola (redirigida)
                }
                return null;
            }

            @Override protected void done() {
                System.setOut(originalOut);
                System.setErr(originalErr);
            }
        }.execute();
    }

    // ---------- Lienzo: grilla + placeholder ----------
    static class CanvasPanel extends JPanel {
        CanvasPanel() { setBackground(Color.WHITE); }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(240, 240, 240));
            int step = 25;
            for (int x = 0; x < getWidth(); x += step) g2.drawLine(x, 0, x, getHeight());
            for (int y = 0; y < getHeight(); y += step) g2.drawLine(0, y, getWidth(), y);

            // placeholder “tortuga”
            g2.setColor(new Color(60, 120, 200));
            int cx = getWidth() / 2, cy = getHeight() / 2, r = 10;
            g2.fillOval(cx - r, cy - r, 2 * r, 2 * r);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString("Tortuga (placeholder)", 10, 20);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        // Look & Feel nativo (opcional)
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new MiniIDE().setVisible(true));
    }
}
