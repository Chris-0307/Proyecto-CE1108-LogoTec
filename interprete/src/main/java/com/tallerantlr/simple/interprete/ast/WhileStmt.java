package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class WhileStmt implements ASTNode {
    private final ASTNode condition;
    private final List<ASTNode> body;

    public WhileStmt(ASTNode condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = condition.execute(symbolTable);
        if (!(v instanceof Boolean)) {
            throw new RuntimeException("La condición de MIENTRAS no es booleana: " + v);
        }
        while ((Boolean) v) {                 // ejecuta mientras la condición sea true
            for (ASTNode n : body) n.execute(symbolTable);
            v = condition.execute(symbolTable);
            if (!(v instanceof Boolean)) {
                throw new RuntimeException("La condición de MIENTRAS no es booleana: " + v);
            }
        }
        return null; // sentencia -> sin valor útil
    }
}
