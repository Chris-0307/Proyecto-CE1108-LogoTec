package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class PonY implements ASTNode {
    private final ASTNode yExpr;

    // Opcional: clave de estado para futuras integraciones
    private static final String KEY_Y = "__y__";

    public PonY(ASTNode yExpr) {
        this.yExpr = yExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object yv = yExpr.execute(symbolTable);
        if (!(yv instanceof Number)) {
            String t = (yv == null) ? "null" : yv.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'pony' requiere Y numérico (actual: " + t + ")");
        }
        int y = ((Number) yv).intValue();

        // Stub: solo imprimir
        System.out.println("la tortuga puso Y = " + y + " (sin dibujar)");

        // (Opcional) Guardar estado para que otras instrucciones lo lean
        // symbolTable.put(KEY_Y, y);

        return null;
    }
}
