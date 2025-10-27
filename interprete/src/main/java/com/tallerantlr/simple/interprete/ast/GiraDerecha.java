package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;

public class GiraDerecha implements ASTNode {
    private final ASTNode angleExpr;

    public GiraDerecha(ASTNode angleExpr) {
        this.angleExpr = angleExpr;
    }

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner
        // InterpreterRunner.DrawingDelegate delegate = (InterpreterRunner.DrawingDelegate) context.get(InterpreterRunner.DRAWING_DELEGATE_KEY); // No necesario aquí pero lo obtenemos por consistencia al evaluar expr

        if (turtleState == null) {
             throw new IllegalStateException("TurtleState no encontrado en el contexto");
        }

        Object v = angleExpr.execute(context);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new SemanticError("Error: 'giraderecha' requiere un número (grados) (actual: " + t + ")", 0, 0);
        }
        double deg = ((Number) v).doubleValue();

        turtleState.turnRight(deg); // Actualizar estado
        return null;
    }
}