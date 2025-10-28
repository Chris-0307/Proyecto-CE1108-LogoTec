package com.tallerantlr.simple.interprete.ast;

import java.util.*;
public class PotenciaN implements ASTNode {
    private final List<ASTNode> args;
    public PotenciaN(List<ASTNode> args) { this.args = args; }

    @Override public Object execute(Map<String,Object> st) {
        if (args.isEmpty()) throw new RuntimeException("Potencia requiere al menos 1 operando");
        // Evalúa de derecha a izquierda: a^(b^(c^...))
        int acc = toInt(args.get(args.size()-1).execute(st));
        for (int i = args.size()-2; i >= 0; i--) {
            int base = toInt(args.get(i).execute(st));
            acc = (int)Math.pow(base, acc);
        }
        return acc;
    }
    private static int toInt(Object o){
        if (!(o instanceof Integer)) throw new RuntimeException("Potencia: operandos enteros");
        return (Integer)o;
    }
}
