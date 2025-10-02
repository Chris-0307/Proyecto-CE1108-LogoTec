package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class PonColorLapiz implements ASTNode {
    private final ASTNode colorExpr;
    private static final String KEY = "__pen_color__";

    public PonColorLapiz(ASTNode colorExpr) {
        this.colorExpr = colorExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = colorExpr.execute(symbolTable);
        if (!(v instanceof String)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: 'poncolorlapiz' requiere un texto (actual: " + t + ")");
        }
        String raw = ((String) v).trim().toLowerCase();
        switch (raw) {
            case "negro":
            case "azul":
            case "rojo":
                symbolTable.put(KEY, raw);
                System.out.println("color del lápiz principal = " + raw);
                return null;
            default:
                throw new RuntimeException(
                    "Error semántico: color inválido '" + v + "'. Valores permitidos: negro, azul, rojo");
        }
    }
}
