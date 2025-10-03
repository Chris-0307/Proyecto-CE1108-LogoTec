package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class Suma implements ASTNode {
    private List<ASTNode> operands;

    public Suma(List<ASTNode> operands) {
        this.operands = operands;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        int result = 0;
        for (ASTNode operand : operands) {
            result += (int) operand.execute(symbolTable);
        }
        return result;
    }
}