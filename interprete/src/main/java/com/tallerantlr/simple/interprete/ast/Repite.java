package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class Repite implements ASTNode {
    private final ASTNode timesExpr;
    private final List<ASTNode> body;

    public Repite(ASTNode timesExpr, List<ASTNode> body) {
        this.timesExpr = timesExpr;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = timesExpr.execute(symbolTable);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'repite' requiere número de iteraciones (actual: " + t + ")");
        }
        double d = ((Number) v).doubleValue();
        // Exigir entero no-negativo
        int n = (int) d;
        if (d != n) {
            throw new RuntimeException("Error semántico: 'repite' requiere un entero (sin decimales), valor: " + d);
        }
        if (n < 0) {
            throw new RuntimeException("Error semántico: 'repite' no acepta valores negativos: " + n);
        }

        for (int i = 0; i < n; i++) {
            for (ASTNode stmt : body) {
                if (stmt != null) stmt.execute(symbolTable);
            }
        }
        return null;
        }
}
