package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class PonRumbo implements ASTNode {
    private final ASTNode angleExpr;

    public PonRumbo(ASTNode angleExpr) {
        this.angleExpr = angleExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = angleExpr.execute(symbolTable);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'ponrumbo' requiere un número (grados) (actual: " + t + ")");
        }
     // en PonRumbo.execute(...)
        int deg = ((Number) v).intValue();
        System.out.println("la tortuga fijó su rumbo a " + deg + " grados");
        // guardar orientación actual para que 'rumbo' la lea
        symbolTable.put("__rumbo__", deg);
        return null;

    }
}
