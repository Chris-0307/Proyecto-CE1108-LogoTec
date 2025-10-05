package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class Diferencia implements ASTNode {
    private final List<ASTNode> terms;

    public Diferencia(List<ASTNode> terms) {
        this.terms = terms;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        if (terms.isEmpty()) return 0;

        Object firstVal = terms.get(0).execute(symbolTable);
        if (!(firstVal instanceof Integer)) {
            throw new RuntimeException("Diferencia: todos los operandos deben ser enteros");
        }

        int result = (Integer) firstVal;
        for (int i = 1; i < terms.size(); i++) {
            Object val = terms.get(i).execute(symbolTable);
            if (!(val instanceof Integer)) {
                throw new RuntimeException("Diferencia: todos los operandos deben ser enteros");
            }
            result -= (Integer) val;
        }
        return result;
    }
}
