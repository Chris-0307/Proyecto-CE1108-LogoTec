package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class GiraDerecha implements ASTNode {
    private final ASTNode angleExpr;

    public GiraDerecha(ASTNode angleExpr) {
        this.angleExpr = angleExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = angleExpr.execute(symbolTable);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'giraderecha' requiere un número (grados) (actual: " + t + ")");
        }
        int deg = ((Number) v).intValue();
        System.out.println("la tortuga giró a la derecha " + deg + " grados");
        // Más adelante aquí actualizarán el estado (ángulo) de la tortuga.
        return null;
    }
}
