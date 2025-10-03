package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class PonX implements ASTNode {
    private final ASTNode xExpr;

    // Opcional: claves para estado futuro
    private static final String KEY_X = "__x__";

    public PonX(ASTNode xExpr) {
        this.xExpr = xExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object xv = xExpr.execute(symbolTable);
        if (!(xv instanceof Number)) {
            String t = (xv == null) ? "null" : xv.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'ponx' requiere X numérico (actual: " + t + ")");
        }
        int x = ((Number) xv).intValue();

        // Stub (solo imprime)
        System.out.println("la tortuga puso X = " + x + " (sin dibujar)");

        // (Opcional) Guardar estado para que otras instrucciones lo lean
        // symbolTable.put(KEY_X, x);

        return null;
    }
}
