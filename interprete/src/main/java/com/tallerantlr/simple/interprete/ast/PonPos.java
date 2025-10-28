package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;

public class PonPos implements ASTNode {
    private final ASTNode xExpr;
    private final ASTNode yExpr;

    public PonPos(ASTNode xExpr, ASTNode yExpr) {
        this.xExpr = xExpr;
        this.yExpr = yExpr;
    }

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner

        if (turtleState == null) {
            throw new IllegalStateException("TurtleState no encontrado en el contexto");
        }

        Object xv = xExpr.execute(context);
        Object yv = yExpr.execute(context);

        if (!(xv instanceof Number)) {
            String t = (xv == null) ? "null" : xv.getClass().getSimpleName();
            throw new SemanticError("Error: 'ponpos/ponxy' requiere X numérico (actual: " + t + ")", 0, 0);
        }
        if (!(yv instanceof Number)) {
            String t = (yv == null) ? "null" : yv.getClass().getSimpleName();
            throw new SemanticError("Error: 'ponpos/ponxy' requiere Y numérico (actual: " + t + ")", 0, 0);
        }

        double x = ((Number) xv).doubleValue();
        double y = ((Number) yv).doubleValue();

        turtleState.setPosition(x, y); // Actualizar posición SIN dibujar
        return null;
    }
}