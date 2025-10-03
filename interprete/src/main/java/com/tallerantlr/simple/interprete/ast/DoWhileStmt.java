package com.tallerantlr.simple.interprete.ast;

import java.util.List;
import java.util.Map;

public class DoWhileStmt implements ASTNode {
    private final ASTNode condition;
    private final List<ASTNode> body;

    public DoWhileStmt(ASTNode condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        while (true) {
            // Ejecuta el cuerpo al menos una vez
            for (ASTNode n : body) {
                n.execute(symbolTable);
            }
            // Evalúa condición; debe ser Boolean
            Object v = condition.execute(symbolTable);
            if (!(v instanceof Boolean)) {
                throw new RuntimeException("La condición de HAZ.MIENTRAS no es booleana: " + v);
            }
            if (!((Boolean) v)) break;  // repetir solo mientras sea true
        }
        return null;
    }
}
