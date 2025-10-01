package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class Permutation implements ASTNode {
    private final ASTNode nNode;
    private final ASTNode rNode;

    public Permutation(ASTNode nNode, ASTNode rNode) {
        this.nNode = nNode;
        this.rNode = rNode;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object nObj = nNode.execute(symbolTable);
        Object rObj = rNode.execute(symbolTable);

        if (!(nObj instanceof Number) || !(rObj instanceof Number)) {
            throw new IllegalArgumentException("Permutación 'P' requiere números (n y r).");
        }

        int n = ((Number) nObj).intValue();
        int r = ((Number) rObj).intValue();

        if (n < 0 || r < 0) {
            throw new IllegalArgumentException("Permutación indefinida para n<0 o r<0.");
        }
        if (r > n) {
            return 0;
        }

        long res = 1L;
        for (int k = n - r + 1; k <= n; k++) {
            res *= k;
            
            if (res > Integer.MAX_VALUE) {
                throw new ArithmeticException("Resultado de nPr excede el rango de 32 bits.");
            }
        }
        return (int) res;
    }
}
