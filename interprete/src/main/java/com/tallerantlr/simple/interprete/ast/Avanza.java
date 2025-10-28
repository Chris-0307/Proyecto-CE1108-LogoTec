package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;
import java.awt.Color;

public class Avanza implements ASTNode {
    private final ASTNode amountExpr;

    public Avanza(ASTNode amountExpr) {
        this.amountExpr = amountExpr;
    }

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner
        InterpreterRunner.DrawingDelegate delegate = (InterpreterRunner.DrawingDelegate) context.get(InterpreterRunner.DRAWING_DELEGATE_KEY); // Usar clave de Runner

        if (turtleState == null || delegate == null) {
            throw new IllegalStateException("TurtleState o DrawingDelegate no encontrados en el contexto");
        }

        Object v = amountExpr.execute(context);
        if (!(v instanceof Number)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new SemanticError("Error: 'avanza' requiere un número (actual: " + t + ")", 0, 0);
        }
        double n = ((Number) v).doubleValue();

        double startX = turtleState.getX();
        double startY = turtleState.getY();

        turtleState.moveForward(n); // Actualiza estado

        if (turtleState.isPenDown()) {
            delegate.addLine(startX, startY, turtleState.getX(), turtleState.getY(), turtleState.getPenColor());
        }
        return null;
    }
}