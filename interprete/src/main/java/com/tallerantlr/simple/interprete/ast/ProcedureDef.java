// ProcedureDef.java
package com.tallerantlr.simple.interprete.ast;

import java.util.*;

public class ProcedureDef {
    private final String name;
    private final List<String> params;
    private final List<ASTNode> body;
    private final Map<String,Object> globals;
    private final Map<String,ProcedureDef> procTable;

    public ProcedureDef(String name, List<String> params, List<ASTNode> body,
                        Map<String,Object> globals,
                        Map<String,ProcedureDef> procTable) {
        this.name = name;
        this.params = params;
        this.body = body;
        this.globals = globals;
        this.procTable = procTable;
    }

    public int arity() { return params.size(); }
    public String name() { return name; }

    public Object call(List<Object> args) {
        if (args.size() != params.size())
            throw new RuntimeException("Aridad incorrecta en " + name + ": se esperaban " + params.size());

        // Entorno local: copia somera de globals + override con params
        Map<String,Object> local = new HashMap<>(globals);
        for (int i = 0; i < params.size(); i++) {
            local.put(params.get(i), args.get(i));
        }
        for (ASTNode n : body) n.execute(local);
        // Propagar cambios locales a globals (si quieres comportamiento “global”)
        globals.putAll(local);
        return null;
    }
}
