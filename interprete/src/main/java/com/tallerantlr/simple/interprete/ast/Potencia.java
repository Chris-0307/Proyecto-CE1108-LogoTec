package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Potencia implements ASTNode {
    private final ASTNode base;
    private final ASTNode exponente;

    public Potencia(ASTNode base, ASTNode exponente) {
        this.base = base;
        this.exponente = exponente;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object bVal = base.execute(symbolTable);
        Object eVal = exponente.execute(symbolTable);

        if (!(bVal instanceof Integer) || !(eVal instanceof Integer)) {
            throw new RuntimeException("Potencia: ambos operandos deben ser enteros");
        }

        return (int) Math.pow((Integer) bVal, (Integer) eVal);
    }
}
