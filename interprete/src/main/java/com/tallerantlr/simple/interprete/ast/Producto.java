package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class Producto implements ASTNode {
    private List<ASTNode> operands;

    public Producto(List<ASTNode> operands) {
        this.operands = operands;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        int result = 1;
        for (ASTNode n : operands) {
            result *= (int) n.execute(symbolTable);
        }
        return result;
    }
}