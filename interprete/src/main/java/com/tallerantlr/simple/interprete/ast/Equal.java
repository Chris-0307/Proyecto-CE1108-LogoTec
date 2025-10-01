package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Equal implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public Equal(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object a = left.execute(symbolTable);
        Object b = right.execute(symbolTable);
        
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        
        if (a instanceof Number && b instanceof Number) {
            return ((Number) a).intValue() == ((Number) b).intValue();
        }
        
        if (a instanceof Boolean && b instanceof Boolean) {
            return ((Boolean) a).booleanValue() == ((Boolean) b).booleanValue();
        }
        
        return a.equals(b);
    }
}
