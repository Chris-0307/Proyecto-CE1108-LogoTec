package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class DoUntilStmt implements ASTNode {
    private final ASTNode condition;
    private final List<ASTNode> body;

    public DoUntilStmt(ASTNode condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        while (true) {
            // 1) Ejecuta el cuerpo
            for (ASTNode n : body) {
                n.execute(symbolTable);
            }
            // 2) Evalúa condición
            Object v = condition.execute(symbolTable);
            if (!(v instanceof Boolean)) {
                throw new RuntimeException("La condición de HAZ.HASTA no es booleana: " + v);
            }
            // 3) Sale si la condición es verdadera (se cumplió)
            if ((Boolean) v) break;
        }
        return null; // sentencia, sin valor útil
    }
}
