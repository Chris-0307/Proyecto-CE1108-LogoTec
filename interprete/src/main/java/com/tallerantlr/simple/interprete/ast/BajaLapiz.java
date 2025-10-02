package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class BajaLapiz implements ASTNode {
    private static final String KEY_PEN = "__pen__";  // "down" | "up" (o null al inicio)

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object pen = symbolTable.get(KEY_PEN);
        if ("down".equals(pen)) {
            // Ya estaba abajo -> no hace nada (idempotente)
            System.out.println("el lápiz ya estaba abajo; no se cambia el estado");
            return null;
        }
        // Si estaba arriba o sin inicializar, bájalo
        symbolTable.put(KEY_PEN, "down");
        System.out.println("la tortuga bajó el lápiz; se dibujará al moverse");
        return null;
    }
}
