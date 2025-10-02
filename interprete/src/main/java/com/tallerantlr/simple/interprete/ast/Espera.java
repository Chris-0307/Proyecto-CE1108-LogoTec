package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Espera implements ASTNode {
    private final ASTNode nExpr;

    public Espera(ASTNode nExpr) {
        this.nExpr = nExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = nExpr.execute(symbolTable);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'espera' requiere un número (actual: " + t + ")");
        }
        double n = ((Number) v).doubleValue();
        if (n < 0) {
            throw new RuntimeException("Error semántico: 'espera' no acepta valores negativos (" + n + ")");
        }
        long millis = Math.round((n / 60.0) * 1000.0);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.println("la tortuga esperó ~" + millis + " ms (" + String.format("%.3f", millis / 1000.0) + " s)");
        return null;
    }
}
