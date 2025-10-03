package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class OcultaTortuga implements ASTNode {
    @Override
    public Object execute(Map<String, Object> symbolTable) {
        // Stub: solo imprime la acción solicitada por ahora
        System.out.println("la tortuga se ocultó y se colocó en la posición inicial (arriba-izquierda)");
        // En el futuro: actualizar estado global de la tortuga (visible=false, x=0, y=0, angle=0, etc.)
        return null;
    }
}
