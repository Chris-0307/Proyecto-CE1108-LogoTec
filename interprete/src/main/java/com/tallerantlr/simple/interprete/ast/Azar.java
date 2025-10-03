package com.tallerantlr.simple.interprete.ast;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Azar implements ASTNode {
    private final ASTNode maxExpr;

    public Azar(ASTNode maxExpr) {
        this.maxExpr = maxExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = maxExpr.execute(symbolTable);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'azar' requiere un número (actual: " + t + ")");
        }
        int n = ((Number) v).intValue();
        if (n < 0) {
            throw new RuntimeException("Error semántico: 'azar' requiere un número >= 0 (actual: " + n + ")");
        }
        // Aleatorio entero en [0, n]
        return ThreadLocalRandom.current().nextInt(n + 1);
    }
}
