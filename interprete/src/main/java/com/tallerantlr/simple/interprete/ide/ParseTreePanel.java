package com.tallerantlr.simple.interprete.ide;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ParseTreePanel extends JPanel {
    public ParseTreePanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Sin árbol"), BorderLayout.CENTER);
    }

    public void setTree(Parser parser, ParseTree tree) {
        removeAll();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.1); // zoom
        JScrollPane scroll = new JScrollPane(viewer);
        add(scroll, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
