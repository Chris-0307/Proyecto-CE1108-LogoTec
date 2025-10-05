package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Inc implements ASTNode {
    private final String name;
    private final ASTNode stepExpr; // cuánto sumar (1 por defecto si se construyó así)

    public Inc(String name, ASTNode stepExpr) {
        this.name = name;
        this.stepExpr = stepExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        if (!symbolTable.containsKey(name)) {
            throw new RuntimeException("Error semántico: 'inc' requiere variable existente: " + name);
        }
        Object cur = symbolTable.get(name);
        if (!(cur instanceof Number)) {
            String t = (cur == null) ? "null" : cur.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: '" + name + "' debe ser numérica (actual: " + t + ")");
        }

        Object stepVal = stepExpr.execute(symbolTable);
        if (!(stepVal instanceof Number)) {
            String t = (stepVal == null) ? "null" : stepVal.getClass().getSimpleName();
            throw new RuntimeException("Error semántico: incremento N2 debe ser numérico (actual: " + t + ")");
        }

        int base = ((Number) cur).intValue();
        int step = ((Number) stepVal).intValue();

        int result = base + step;
        symbolTable.put(name, result);
        return null;
    }
}
