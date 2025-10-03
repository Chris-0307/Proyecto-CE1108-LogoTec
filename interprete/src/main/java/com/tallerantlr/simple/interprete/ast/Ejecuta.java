package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class Ejecuta implements ASTNode {
    private final List<ASTNode> body;

    public Ejecuta(List<ASTNode> body) {
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        for (ASTNode n : body) {
            if (n != null) n.execute(symbolTable);
        }
        return null;
    }
}
