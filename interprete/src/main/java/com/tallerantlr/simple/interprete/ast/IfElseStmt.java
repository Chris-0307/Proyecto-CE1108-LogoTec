package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class IfElseStmt implements ASTNode {
    private final ASTNode condition;
    private final List<ASTNode> thenBody;
    private final List<ASTNode> elseBody;

    public IfElseStmt(ASTNode condition, List<ASTNode> thenBody, List<ASTNode> elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = condition.execute(symbolTable);
        if (!(v instanceof Boolean)) {
            throw new RuntimeException("La condición del SI no es booleana: " + v);
        }
        List<ASTNode> body = ((Boolean) v) ? thenBody : elseBody;
        for (ASTNode n : body) {
            n.execute(symbolTable);
        }
        return null; // sentencia -> sin valor útil
    }
}
