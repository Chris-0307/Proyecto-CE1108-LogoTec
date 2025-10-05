package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class HazAssign implements ASTNode {
    private final String name;
    private final ASTNode valueExpr;

    public HazAssign(String name, ASTNode valueExpr) {
        this.name = name;
        this.valueExpr = valueExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        // Declaración sin valor inicial: 'Haz x'
        if (valueExpr == null) {
            if (!symbolTable.containsKey(name)) {
                symbolTable.put(name, null);   // sin tipo aún
            }
            return null;
        }

        Object newVal = valueExpr.execute(symbolTable);
        boolean exists = symbolTable.containsKey(name);

        if (!exists) {
            // Primera vez con valor: declara y fija tipo
            symbolTable.put(name, newVal);
            return null;
        }

        Object oldVal = symbolTable.get(name);

        // Reasignación permitida si el tipo coincide (o ambos null)
        if ((oldVal == null && newVal == null) ||
            (oldVal != null && newVal != null && oldVal.getClass().equals(newVal.getClass()))) {
            symbolTable.put(name, newVal);
            return null;
        }

        String tOld = (oldVal == null) ? "null" : oldVal.getClass().getSimpleName();
        String tNew = (newVal == null) ? "null" : newVal.getClass().getSimpleName();
        throw new RuntimeException("Error semántico: variable '" + name +
                                   "' es de tipo " + tOld + " y se intentó asignar " + tNew);
    }

}
