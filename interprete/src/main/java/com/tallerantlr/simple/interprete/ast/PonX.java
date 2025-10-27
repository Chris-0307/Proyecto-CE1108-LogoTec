package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;
import java.awt.Color; // Importar

public class PonX implements ASTNode {
    private final ASTNode xExpr;

    public PonX(ASTNode xExpr) {
        this.xExpr = xExpr;
    }

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner
        InterpreterRunner.DrawingDelegate delegate = (InterpreterRunner.DrawingDelegate) context.get(InterpreterRunner.DRAWING_DELEGATE_KEY); // Usar clave de Runner

        if (turtleState == null || delegate == null) {
            throw new IllegalStateException("TurtleState o DrawingDelegate no encontrados en el contexto");
        }

        Object xv = xExpr.execute(context);
        if (!(xv instanceof Number)) {
            String t = (xv == null) ? "null" : xv.getClass().getSimpleName();
            throw new SemanticError("Error: 'ponx' requiere X numérico (actual: " + t + ")", 0, 0);
        }
        double x = ((Number) xv).doubleValue();

        double startX = turtleState.getX();
        double startY = turtleState.getY(); // Y no cambia

        turtleState.setX(x); // Actualizar solo X en el estado

        if (turtleState.isPenDown()) {
            delegate.addLine(startX, startY, turtleState.getX(), startY, turtleState.getPenColor());
        }
        return null;
    }
}