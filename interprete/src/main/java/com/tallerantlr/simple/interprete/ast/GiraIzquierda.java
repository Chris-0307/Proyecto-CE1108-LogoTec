package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;

public class GiraIzquierda implements ASTNode {
    private final ASTNode angleExpr;

    public GiraIzquierda(ASTNode angleExpr) {
        this.angleExpr = angleExpr;
    }

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner

        if (turtleState == null) {
            throw new IllegalStateException("TurtleState no encontrado en el contexto");
        }

        Object v = angleExpr.execute(context);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new SemanticError("Error: 'giraizquierda' requiere un número (grados) (actual: " + t + ")", 0, 0);
        }
        double deg = ((Number) v).doubleValue();

        turtleState.turnLeft(deg); // Actualizar estado
        return null;
    }
}