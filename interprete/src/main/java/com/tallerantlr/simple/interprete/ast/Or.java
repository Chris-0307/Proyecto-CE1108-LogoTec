// Or.java
package com.tallerantlr.simple.interprete.ast;
import java.util.Map;

public class Or implements ASTNode {
    private final ASTNode left, right;
    public Or(ASTNode left, ASTNode right) { this.left = left; this.right = right; }

    @Override public Object execute(Map<String, Object> st) {
        Object l = left.execute(st);
        if (!(l instanceof Boolean)) throw new RuntimeException("O: operandos booleanos");
        if ((Boolean) l) return true;                   // cortocircuito
        Object r = right.execute(st);
        if (!(r instanceof Boolean)) throw new RuntimeException("O: operandos booleanos");
        return (Boolean) r;
    }
}
