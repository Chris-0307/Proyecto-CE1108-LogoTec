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
        Object newVal = valueExpr.execute(symbolTable);
        Object oldVal = symbolTable.get(name);

        if (oldVal == null) {
            // Primera asignación: fija el “tipo” con el valor inicial
            symbolTable.put(name, newVal);
            return null;
        }

        // Si ya hay valor previo, verifica “tipo” (clase Java)
        if (oldVal.getClass().equals(newVal != null ? newVal.getClass() : null)) {
            symbolTable.put(name, newVal);
            return null;
        }

        String tOld = (oldVal == null) ? "null" : oldVal.getClass().getSimpleName();
        String tNew = (newVal == null) ? "null" : newVal.getClass().getSimpleName();
        throw new RuntimeException("Error semántico: variable '" + name +
                                   "' es de tipo " + tOld + " y se intentó asignar " + tNew);
    }
}
