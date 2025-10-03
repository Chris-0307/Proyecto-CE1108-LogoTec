package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Centro implements ASTNode {
    // Si luego manejas estado real (ancho/alto), aquí podrías calcular (cx, cy)
    // y actualizar __x__/__y__. Por ahora solo imprime.
    @Override
    public Object execute(Map<String, Object> symbolTable) {
        System.out.println("la tortuga se posicionó en el centro (sin dibujar)");
        return null;
    }
}
