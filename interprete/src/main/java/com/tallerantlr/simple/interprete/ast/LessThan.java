package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class LessThan implements ASTNode {
    private final ASTNode left, right;

    public LessThan(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object a = left.execute(symbolTable);
        Object b = right.execute(symbolTable);
        if (!(a instanceof Integer) || !(b instanceof Integer)) {
            throw new RuntimeException("'<' : se esperaban enteros");
        }
        return ((int) a) < ((int) b);
    }
}
