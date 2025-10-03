package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class PonPos implements ASTNode {
    private final ASTNode xExpr;
    private final ASTNode yExpr;

    public PonPos(ASTNode xExpr, ASTNode yExpr) {
        this.xExpr = xExpr;
        this.yExpr = yExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object xv = xExpr.execute(symbolTable);
        Object yv = yExpr.execute(symbolTable);

        if (!(xv instanceof Number)) {
            String t = (xv == null) ? "null" : xv.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'ponpos/ponxy' requiere X numérico (actual: " + t + ")");
        }
        if (!(yv instanceof Number)) {
            String t = (yv == null) ? "null" : yv.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'ponpos/ponxy' requiere Y numérico (actual: " + t + ")");
        }

        int x = ((Number) xv).intValue();
        int y = ((Number) yv).intValue();

        System.out.println("la tortuga se posicionó en (" + x + ", " + y + ") sin dibujar");
        // Futuro: actualizar estado (p. ej., TurtleState.x/y) sin trazar línea.
        return null;
    }
}
