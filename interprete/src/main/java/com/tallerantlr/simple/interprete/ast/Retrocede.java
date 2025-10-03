package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Retrocede implements ASTNode {
    private final ASTNode amountExpr;

    public Retrocede(ASTNode amountExpr) {
        this.amountExpr = amountExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = amountExpr.execute(symbolTable);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'retrocede' requiere un número (actual: " + t + ")");
        }
        int n = ((Number) v).intValue();
        System.out.println("la tortuga retrocedió " + n + " unidades");
        // Más adelante aquí actualizarán el estado de la tortuga
        return null;
    }
}
