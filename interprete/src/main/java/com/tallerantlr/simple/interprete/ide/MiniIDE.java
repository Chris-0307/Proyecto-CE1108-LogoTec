package com.tallerantlr.simple.interprete.ide;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.PrintStream;

public class MiniIDE extends JFrame {
    private final JTextArea editor = new JTextArea();
    private final JTextArea console = new JTextArea();
    private final CanvasPanel canvas = new CanvasPanel();
    private final ParseTreePanel parsePanel = new ParseTreePanel(); // <<< NUEVO

    public MiniIDE() {
        super("Logo-lite IDE");

        // Editor
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        editor.setText("// Demo\nvar a = 3\nprintln(\"hola\")\n");

        // Consola
        console.setEditable(false);
        console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        console.setBackground(new Color(250, 250, 250));
        console.setBorder(new EmptyBorder(6,6,6,6));

        // Botón ejecutar
        JButton runBtn = new JButton("Ejecutar");
        runBtn.addActionListener(e -> runProgram());
        
        JButton treeBtn = new JButton("Actualizar árbol"); // opcional
        treeBtn.addActionListener(e -> updateParseTree());

        // Panel derecho medio: editor/consola
        JSplitPane rightMiddle = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                wrapWithTitled(new JScrollPane(editor), "Editor"),
                wrapWithTitled(new JScrollPane(console), "Consola"));
        rightMiddle.setResizeWeight(0.7);

        // Panel derecho externo: editor+consola vs arbol
        JSplitPane right = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                rightMiddle,
                wrapWithTitled(parsePanel, "Árbol de parseo"));
        right.setResizeWeight(0.65);

        // Split principal: lienzo | (editor+consola | arbol)
        JSplitPane main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                wrapWithTitled(canvas, "Lienzo"),
                right);
        main.setResizeWeight(0.35);

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(runBtn);
        topBar.add(treeBtn);

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

    
    private void updateParseTree() {
        // Parsear sin ejecutar
        try {
            InterpreterRunner.parseOnly(editor.getText(), parsePanel, s -> console.append(s + "\n"));
        } catch (Exception ex) {
            console.append("[ERR] " + ex.getMessage() + "\n");
        }
    }
    
    private void runProgram() {
        console.setText("");
        updateParseTree();
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
                    // Ejecuta el intérprete con el contenido del editor
                    InterpreterRunner.runFromString(
                            editor.getText(),
                            s -> console.append(s + "\n"),
                            s -> console.append(s + "\n")
                    );
                    // TODO: cuando implementes la tortuga, actualiza el estado en canvas y llama canvas.repaint()
                } catch (Throwable t) {
                    t.printStackTrace(); // irá a la consola (redirigida)
                }
                return null;
            }

            @Override protected void done() {
                System.setOut(originalOut);
                System.setErr(originalErr);
            }
        }.execute();
    }

    // Lienzo: grilla + placeholder
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
            int cx = getWidth()/2, cy = getHeight()/2, r = 10;
            g2.fillOval(cx - r, cy - r, 2*r, 2*r);
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
