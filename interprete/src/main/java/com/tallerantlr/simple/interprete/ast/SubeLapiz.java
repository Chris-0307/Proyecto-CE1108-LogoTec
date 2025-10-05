package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class SubeLapiz implements ASTNode {
    private static final String KEY_PEN = "__pen__";  // "down" | "up" | null

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object pen = symbolTable.get(KEY_PEN);
        if ("up".equals(pen)) {
            System.out.println("el lápiz ya estaba arriba; no se cambia el estado");
            return null;
        }
        symbolTable.put(KEY_PEN, "up");
        System.out.println("la tortuga subió el lápiz; no se dibujará al moverse");
        return null;
    }
}
