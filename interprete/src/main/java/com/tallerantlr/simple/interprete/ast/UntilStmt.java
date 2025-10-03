package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class UntilStmt implements ASTNode {
    private final ASTNode condition;
    private final List<ASTNode> body;

    public UntilStmt(ASTNode condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object v = condition.execute(symbolTable);
        if (!(v instanceof Boolean)) {
            throw new RuntimeException("La condición de HASTA no es booleana: " + v);
        }
        while (!((Boolean) v)) {                // mientras la condición sea falsa, ejecuta
            for (ASTNode n : body) n.execute(symbolTable);
            v = condition.execute(symbolTable);
            if (!(v instanceof Boolean)) {
                throw new RuntimeException("La condición de HASTA no es booleana: " + v);
            }
        }
        return null; // sentencia -> sin valor de retorno
    }
}
