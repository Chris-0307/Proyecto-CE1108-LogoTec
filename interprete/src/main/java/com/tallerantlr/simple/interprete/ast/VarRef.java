package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class VarRef implements ASTNode {

    private final String name;
    private final int line;
    private final int charPos;

    public VarRef(String name, int line, int charPos) {
        this.name = name;
        this.line = line;
        this.charPos = charPos;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        // 1) No declarada
        if (!symbolTable.containsKey(name)) {
            throw new SemanticError("Error semántico: variable no declarada: " + name, line, charPos);
        }

        Object val = symbolTable.get(name);

        // 2) Declarada pero no inicializada
        if (val == null) {
            throw new SemanticError("Error semántico: variable '" + name + "' no inicializada", line, charPos);
        }

        return val;
    }
}
