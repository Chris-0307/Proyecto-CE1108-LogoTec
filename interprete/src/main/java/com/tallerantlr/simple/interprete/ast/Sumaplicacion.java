package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Sumaplicacion implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public Sumaplicacion(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object aObj = left.execute(symbolTable);
        Object bObj = right.execute(symbolTable);

        int a = (int) aObj;
        int b = (int) bObj;

     
        return a + b + (a * b);
    }
}