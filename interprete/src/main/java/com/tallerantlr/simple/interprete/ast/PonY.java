package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;
import java.awt.Color; // Importar

public class PonY implements ASTNode {
    private final ASTNode yExpr;

    public PonY(ASTNode yExpr) {
        this.yExpr = yExpr;
    }

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner
        InterpreterRunner.DrawingDelegate delegate = (InterpreterRunner.DrawingDelegate) context.get(InterpreterRunner.DRAWING_DELEGATE_KEY); // Usar clave de Runner

        if (turtleState == null || delegate == null) {
            throw new IllegalStateException("TurtleState o DrawingDelegate no encontrados en el contexto");
        }

        Object yv = yExpr.execute(context);
        if (!(yv instanceof Number)) {
            String t = (yv == null) ? "null" : yv.getClass().getSimpleName();
            throw new SemanticError("Error: 'pony' requiere Y numérico (actual: " + t + ")", 0, 0);
        }
        double y = ((Number) yv).doubleValue();

        double startX = turtleState.getX(); // X no cambia
        double startY = turtleState.getY();

        turtleState.setY(y); // Actualizar solo Y en el estado

        if (turtleState.isPenDown()) {
            delegate.addLine(startX, startY, startX, turtleState.getY(), turtleState.getPenColor());
        }
        return null;
    }
}