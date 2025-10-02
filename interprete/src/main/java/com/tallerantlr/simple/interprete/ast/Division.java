package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Division implements ASTNode {
    private ASTNode operand1;
    private ASTNode operand2;

    public Division(ASTNode operand1, ASTNode operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        int numerator = (int) operand1.execute(symbolTable);
        int denominator = (int) operand2.execute(symbolTable);

        if (denominator == 0) {
            throw new ArithmeticException("Error: división por cero");
        }
        return numerator / denominator; // división entera
    }
}
