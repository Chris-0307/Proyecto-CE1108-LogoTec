package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Rumbo implements ASTNode {
    private static final String KEY = "__rumbo__";

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = symbolTable.get(KEY);
        int deg = (v instanceof Number) ? ((Number) v).intValue() : 0;
        System.out.println("la tortuga está orientada a " + deg + " grados");
        return deg; // por si luego lo quieres usar como expresión; ahora solo imprime
    }
}
