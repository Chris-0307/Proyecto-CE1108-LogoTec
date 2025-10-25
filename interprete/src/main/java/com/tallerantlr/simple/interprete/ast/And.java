// And.java
package com.tallerantlr.simple.interprete.ast;
import java.util.Map;

public class And implements ASTNode {
    private final ASTNode left, right;
    public And(ASTNode left, ASTNode right) { this.left = left; this.right = right; }

    @Override public Object execute(Map<String, Object> st) {
        Object l = left.execute(st);
        if (!(l instanceof Boolean)) throw new RuntimeException("Y: operandos booleanos");
        if (!((Boolean) l)) return false;               // cortocircuito
        Object r = right.execute(st);
        if (!(r instanceof Boolean)) throw new RuntimeException("Y: operandos booleanos");
        return (Boolean) r;
    }
}
