package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;

public class OcultaTortuga implements ASTNode {

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner
        if (turtleState == null) {
             throw new IllegalStateException("TurtleState no encontrado en el contexto");
        }

        turtleState.setVisible(false);

        // Mover a esquina superior izquierda (con valores placeholder)
        double logicalWidth = 400; // Placeholder - Ajustar si es necesario
        double logicalHeight = 400; // Placeholder - Ajustar si es necesario
        turtleState.goToOriginTopLeft(logicalWidth, logicalHeight);
        return null;
    }
}