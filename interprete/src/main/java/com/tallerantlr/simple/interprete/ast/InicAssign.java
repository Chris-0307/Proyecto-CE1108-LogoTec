package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class InicAssign implements ASTNode {
    private final String name;
    private final ASTNode valueExpr;

    public InicAssign(String name, ASTNode valueExpr) {
        this.name = name;
        this.valueExpr = valueExpr;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        // Debe existir la variable (declarada con var o creada con Haz)
        if (!symbolTable.containsKey(name)) {
            throw new RuntimeException("Error semántico: 'inic' requiere variable existente: " + name);
        }

        Object newVal = valueExpr.execute(symbolTable);
        Object oldVal = symbolTable.get(name);

        // Si estaba sin valor (null), primera inicialización: fija tipo
        if (oldVal == null) {
            symbolTable.put(name, newVal);
            return null;
        }

        // Si ya tenía valor, tipos deben coincidir
        if (newVal == null || !oldVal.getClass().equals(newVal.getClass())) {
            String tOld = oldVal.getClass().getSimpleName();
            String tNew = (newVal == null) ? "null" : newVal.getClass().getSimpleName();
            throw new RuntimeException(
                "Error semántico: variable '" + name + "' es de tipo " + tOld + " y se intentó asignar " + tNew
            );
        }

        symbolTable.put(name, newVal);
        return null;
    }
}
