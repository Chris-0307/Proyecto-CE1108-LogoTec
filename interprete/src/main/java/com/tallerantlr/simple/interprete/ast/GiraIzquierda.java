package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class GiraIzquierda implements ASTNode {
    private final ASTNode angleExpr;

    public GiraIzquierda(ASTNode angleExpr) {
        this.angleExpr = angleExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = angleExpr.execute(symbolTable);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'giraizquierda' requiere un número (grados) (actual: " + t + ")");
        }
        int deg = ((Number) v).intValue();
        System.out.println("la tortuga giró a la izquierda " + deg + " grados");
        // Más adelante: actualizar TurtleState.angle = (angle - deg) % 360
        return null;
    }
}
