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

        int n = params.size();
        Object[] saved = new Object[n];
        boolean[] existed = new boolean[n];

        // 1) Sombrar parámetros en el MISMO mapa 'globals'
        for (int i = 0; i < n; i++) {
            String p = params.get(i);
            existed[i] = globals.containsKey(p);
            if (existed[i]) saved[i] = globals.get(p);
            globals.put(p, args.get(i));
        }

        try {
            // 2) Ejecutar cuerpo con los parámetros sombreados
            for (ASTNode s : body) s.execute(globals);
            return null;
        } finally {
            // 3) Restaurar el estado previo de los parámetros
            for (int i = 0; i < n; i++) {
                String p = params.get(i);
                if (existed[i]) globals.put(p, saved[i]);
                else globals.remove(p);
            }
        }
    }
}
