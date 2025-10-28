package com.tallerantlr.simple.interprete.ast;

import com.tallerantlr.simple.interprete.TurtleState;
import com.tallerantlr.simple.interprete.ide.InterpreterRunner; // Importar
import java.util.Map;
import java.awt.Color; // Importar Color

public class PonColorLapiz implements ASTNode {
    private final ASTNode colorExpr;

    public PonColorLapiz(ASTNode colorExpr) {
        this.colorExpr = colorExpr;
    }

    @Override
    public Object execute(Map<String, Object> context) {
        TurtleState turtleState = (TurtleState) context.get(InterpreterRunner.TURTLE_STATE_KEY); // Usar clave de Runner

        if (turtleState == null) {
            throw new IllegalStateException("TurtleState no encontrado en el contexto");
        }

        Object v = colorExpr.execute(context);
        if (!(v instanceof String)) {
            String t = (v == null) ? "null" : v.getClass().getSimpleName();
            throw new SemanticError("Error: 'poncolorlapiz' requiere un texto (actual: " + t + ")", 0, 0);
        }
        String raw = ((String) v).trim().toLowerCase();

        Color chosenColor;
        switch (raw) {
            case "negro": chosenColor = Color.BLACK; break;
            case "azul":  chosenColor = Color.BLUE;  break;
            case "rojo":  chosenColor = Color.RED;   break;
            default:
                throw new SemanticError(
                    "Error: color inválido '" + v + "'. Permitidos: negro, azul, rojo", 0, 0);
        }

        turtleState.setPenColor(chosenColor); // Actualizar estado
        return null;
    }
}