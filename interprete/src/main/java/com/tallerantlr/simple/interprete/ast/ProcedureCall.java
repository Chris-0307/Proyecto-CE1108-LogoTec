// ProcedureCall.java
package com.tallerantlr.simple.interprete.ast;

import java.util.*;

public class ProcedureCall implements ASTNode {
    private final ProcedureDef target;
    private final List<ASTNode> argExprs;

    public ProcedureCall(ProcedureDef target, List<ASTNode> argExprs) {
        this.target = target;
        this.argExprs = argExprs;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        List<Object> args = new ArrayList<>();
        for (ASTNode e : argExprs) args.add(e.execute(symbolTable));
        return target.call(args);
    }
}
