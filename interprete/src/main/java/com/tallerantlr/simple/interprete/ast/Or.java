package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Or implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public Or(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object lVal = left.execute(symbolTable);
        Object rVal = right.execute(symbolTable);

        if (!(lVal instanceof Boolean) || !(rVal instanceof Boolean)) {
            throw new RuntimeException("O: ambos operandos deben ser booleanos");
        }

        return (Boolean) lVal || (Boolean) rVal;
    }
}
