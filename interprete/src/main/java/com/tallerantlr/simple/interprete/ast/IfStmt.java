package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class IfStmt implements ASTNode {
    private final ASTNode condition;
    private final List<ASTNode> body;

    public IfStmt(ASTNode condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = condition.execute(symbolTable);
        if (!(v instanceof Boolean)) {
            throw new RuntimeException("La condición del SI no es booleana: " + v);
        }
        if ((Boolean) v) {
            for (ASTNode n : body) {
                n.execute(symbolTable);
            }
        }
        return null; // las sentencias no retornan valor útil
    }
}
