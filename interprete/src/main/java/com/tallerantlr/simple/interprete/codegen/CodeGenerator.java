package com.tallerantlr.simple.interprete.codegen;

import com.tallerantlr.simple.interprete.ast.*;
import com.tallerantlr.simple.interprete.ir.IR.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Generador de IR (TAC) robusto ante ASTs sin getters públicos.
 * - Sin lambdas ni method references (compatible con Java 7/8 antiguos).
 * - Acceso por reflexión a campos privados con nombres comunes.
 * - Cubre TODAS las construcciones indicadas en la gramática compartida.
 */
public class CodeGenerator {

    // ===== Estado de módulo =====
    private final IRModule mod = new IRModule();
    private int tempCounter = 0;
    private int labelCounter = 0;
    
    private final Set<String> globalNames = new HashSet<>();
    public CodeGenerator() {}
    public CodeGenerator(Set<String> globalNames) {
        if (globalNames != null) this.globalNames.addAll(globalNames);
    }

    // ===== Entorno por función =====
    private class Env {
        final IRFunction fn;
        final Map<String, IRVar> vars = new HashMap<>();
        Env(IRFunction fn){ this.fn = fn; }

        IRVar ensureVar(String name){
            IRVar v = vars.get(name);
            if (v == null) {
                v = new IRVar(name);
                vars.put(name, v);
                if (globalNames.contains(name)) {
                    // Global: NO la declares como local; solo anótala en el módulo
                    mod.globals.add(name);
                } else {
                    // Local real de la función
                    fn.addLocal(name);
                }
            }
            return v;
        }
    }

    private IRTemp newTemp(){ return new IRTemp("t" + (++tempCounter)); }
    private IRLabel newLabel(String base){ return new IRLabel(base + "_" + (++labelCounter)); }

    public IRModule getModule(){ return mod; }

    // ==========================================================
    //                      API PRINCIPAL
    // ==========================================================
    public void generateProgram(List<ASTNode> topLevelBody, Map<String, ProcedureDef> procTable) {
        // 1) Generar funciones de usuario (sin ordenar para máxima compatibilidad)
        if (procTable != null) {
            for (ProcedureDef def : procTable.values()) {
                generateProcedure(def);
            }
        }
        // 2) Función "main"
        IRFunction main = mod.newFunction("main");
        Env env = new Env(main);
        if (topLevelBody != null) {
            for (ASTNode s : topLevelBody) generateStmt(env, s);
        }
        main.code.add(new IRReturn(null));
    }

    private void generateProcedure(ProcedureDef def) {
        String name = str(def, "getName", "name", "id");
        if (name == null) name = "proc_" + (++labelCounter);

        IRFunction fn = mod.newFunction(name);
        Env env = new Env(fn);

        // parámetros
        List<?> params = list(def, "getFormalParams", "formalParams", "params", "parameters");
        if (params != null) {
            for (Object p : params) {
                if (p != null) {
                    String pn = String.valueOf(p);
                    fn.params.add(pn);
                    env.vars.put(pn, new IRVar(pn));
                }
            }
        }

        // cuerpo
        List<ASTNode> body = nlist(def, "getBody", "body", "stmts", "statements", "list");
        if (body != null) {
            for (ASTNode s : body) generateStmt(env, s);
        }

        fn.code.add(new IRReturn(null));
    }

    // ==========================================================
    //                      SENTENCIAS
    // ==========================================================
    private void generateStmt(Env env, ASTNode s) {        // --- E/S ---
    	// --- E/S ---
    	if (s instanceof Println) {
    	    // Intento normal por nombres comunes
    	    ASTNode exprNode = node(s,
    	            "getExpr","expr","expression","exp","e","value","val","arg","argument","node","child");

    	    // Fallback: si sigue null, toma el primer campo ASTNode de la clase Println
    	    if (exprNode == null) exprNode = firstAstChild(s);

    	    IRValue v = genExpr(env, exprNode);
    	    env.fn.code.add(new IRPrintln(v));
    	    return;
    	}


        // --- Asignaciones/Declaraciones ---
        if (s instanceof VarAssign) {
            String name = str(s, "getName", "name", "id", "var", "varName");
            IRVar var = env.ensureVar(name);
            IRValue rhs = genExpr(env, node(s, "getExpr", "expr", "valueExpr", "rhs", "value", "e"));
            env.fn.code.add(new IRMove(var, rhs));
            return;
        }
        if (s instanceof HazAssign) {
            String name = str(s, "getName", "name", "id");
            IRVar var = env.ensureVar(name);
            ASTNode v = node(s, "getValueExpr", "valueExpr", "expr", "value", "rhs");
            if (v != null) {
                env.fn.code.add(new IRMove(var, genExpr(env, v)));
            } else {
                env.fn.code.add(new IRNop().c("decl " + name));
            }
            return;
        }
        if (s instanceof InicAssign) {
            String name = str(s, "getName", "name", "id");
            IRVar var = env.ensureVar(name);
            IRValue rhs = genExpr(env, node(s, "getExpr", "expr", "valueExpr", "value", "rhs"));
            env.fn.code.add(new IRMove(var, rhs));
            return;
        }
        if (s instanceof Inc) {
            String name = str(s, "getName", "name", "id");
            IRVar var = env.ensureVar(name);
            IRValue step = genExpr(env, node(s, "getStepExpr", "stepExpr", "expr", "valueExpr", "value"));
            IRTemp t = newTemp();
            env.fn.code.add(new IRBinOpInstr(t, var, step, IROp.ADD));
            env.fn.code.add(new IRMove(var, t));
            return;
        }

        // --- Condicionales ---
        if (s instanceof IfStmt) {
            IRValue c = genExpr(env, node(s, "getCond", "cond", "condition", "expr"));
            IRLabel Ltrue = newLabel("if_then");
            IRLabel Lend  = newLabel("if_end");
            env.fn.code.add(new IRIfGoto(c, Ltrue));
            env.fn.code.add(new IRGoto(Lend));
            env.fn.code.add(new IRLabelInstr(Ltrue));
            for (ASTNode ss : nlist(s, "getBody", "body", "stmts", "statements")) generateStmt(env, ss);
            env.fn.code.add(new IRLabelInstr(Lend));
            return;
        }
        if (s instanceof IfElseStmt) {
            IRValue c = genExpr(env, node(s, "getCond", "cond", "condition", "expr"));
            IRLabel Ltrue = newLabel("if_then");
            IRLabel Lfalse= newLabel("if_else");
            IRLabel Lend  = newLabel("if_end");
            env.fn.code.add(new IRIfGoto(c, Ltrue));
            env.fn.code.add(new IRGoto(Lfalse));
            env.fn.code.add(new IRLabelInstr(Ltrue));
            for (ASTNode ss : nlist(s, "getThenBody", "thenBody", "then", "body")) generateStmt(env, ss);
            env.fn.code.add(new IRGoto(Lend));
            env.fn.code.add(new IRLabelInstr(Lfalse));
            for (ASTNode ss : nlist(s, "getElseBody", "elseBody", "elsePart", "otherwise")) generateStmt(env, ss);
            env.fn.code.add(new IRLabelInstr(Lend));
            return;
        }

        // --- Bucles ---
        if (s instanceof WhileStmt) {
            IRLabel Lcond = newLabel("while_cond");
            IRLabel Lbody = newLabel("while_body");
            IRLabel Lend  = newLabel("while_end");
            env.fn.code.add(new IRLabelInstr(Lcond));
            IRValue c = genExpr(env, node(s, "getCond", "cond", "condition", "expr"));
            env.fn.code.add(new IRIfGoto(c, Lbody));
            env.fn.code.add(new IRGoto(Lend));
            env.fn.code.add(new IRLabelInstr(Lbody));
            for (ASTNode ss : nlist(s, "getBody", "body", "stmts", "statements")) generateStmt(env, ss);
            env.fn.code.add(new IRGoto(Lcond));
            env.fn.code.add(new IRLabelInstr(Lend));
            return;
        }
        if (s instanceof DoWhileStmt) {
            IRLabel Lbody = newLabel("dowhile_body");
            IRLabel Lcond = newLabel("dowhile_cond");
            env.fn.code.add(new IRLabelInstr(Lbody));
            for (ASTNode ss : nlist(s, "getBody", "body", "stmts", "statements")) generateStmt(env, ss);
            env.fn.code.add(new IRLabelInstr(Lcond));
            IRValue c = genExpr(env, node(s, "getCond", "cond", "condition", "expr"));
            env.fn.code.add(new IRIfGoto(c, Lbody));
            return;
        }
        if (s instanceof UntilStmt) { // HASTA(cond) [body]  => while (!cond) { body }
            IRLabel Lc = newLabel("until_cond");
            IRLabel Lb = newLabel("until_body");
            IRLabel Le = newLabel("until_end");
            env.fn.code.add(new IRLabelInstr(Lc));
            IRValue cond = genExpr(env, node(s, "getCond", "cond", "condition", "expr"));
            IRTemp not = newTemp();
            env.fn.code.add(new IRUnaryOpInstr(not, cond, IROp.NOT));
            env.fn.code.add(new IRIfGoto(not, Lb));
            env.fn.code.add(new IRGoto(Le));
            env.fn.code.add(new IRLabelInstr(Lb));
            for (ASTNode ss : nlist(s, "getBody", "body", "stmts", "statements")) generateStmt(env, ss);
            env.fn.code.add(new IRGoto(Lc));
            env.fn.code.add(new IRLabelInstr(Le));
            return;
        }
        if (s instanceof DoUntilStmt) { // do { body } until(cond)
            IRLabel Lb = newLabel("dountil_body");
            env.fn.code.add(new IRLabelInstr(Lb));
            for (ASTNode ss : nlist(s, "getBody", "body", "stmts", "statements")) generateStmt(env, ss);
            IRValue c = genExpr(env, node(s, "getCond", "cond", "condition", "expr"));
            IRTemp not = newTemp();
            env.fn.code.add(new IRUnaryOpInstr(not, c, IROp.NOT));
            env.fn.code.add(new IRIfGoto(not, Lb));
            return;
        }
        if (s instanceof Repite) { // REPITE n [body]
            IRValue times = genExpr(env, node(s, "getTimes", "times", "timesExpr", "n", "e"));
            IRVar i = env.ensureVar(newTemp().name);
            env.fn.code.add(new IRMove(i, new IRConst(0)));
            IRLabel Lc = newLabel("repite_cond");
            IRLabel Lb = newLabel("repite_body");
            IRLabel Le = newLabel("repite_end");
            env.fn.code.add(new IRLabelInstr(Lc));
            IRTemp cmp = newTemp();
            env.fn.code.add(new IRBinOpInstr(cmp, i, times, IROp.LT));
            env.fn.code.add(new IRIfGoto(cmp, Lb));
            env.fn.code.add(new IRGoto(Le));
            env.fn.code.add(new IRLabelInstr(Lb));
            for (ASTNode ss : nlist(s, "getBody", "body", "stmts", "statements")) generateStmt(env, ss);
            IRTemp inc = newTemp();
            env.fn.code.add(new IRBinOpInstr(inc, i, new IRConst(1), IROp.ADD));
            env.fn.code.add(new IRMove(i, inc));
            env.fn.code.add(new IRGoto(Lc));
            env.fn.code.add(new IRLabelInstr(Le));
            return;
        }
        if (s instanceof Ejecuta) {
            for (ASTNode ss : nlist(s, "getList", "list", "stmts", "statements", "body")) generateStmt(env, ss);
            return;
        }

        // --- Procedimientos ---
        if (s instanceof ProcedureCall) {
            ProcedureDef def = (ProcedureDef) read(s, "getDef", "def", "target", "procedure", "proc");
            List<ASTNode> args = nlist(s, "getArgs", "args", "argExprs", "arguments", "params");
            List<IRValue> a = new ArrayList<IRValue>();
            if (args != null) for (ASTNode e : args) a.add(genExpr(env, e));
            String callee = (def != null) ? str(def, "getName", "name", "id") : "anon_proc";
            env.fn.code.add(new IRCall(callee, a, null));
            return;
        }

     // --- Tortuga / Dibujo ---
        if (s instanceof Avanza)         { callTurtle1(env, "avanza", s); return; }
        if (s instanceof Retrocede)      { callTurtle1(env, "retrocede", s); return; }
        if (s instanceof GiraDerecha)    { callTurtle1(env, "giraDerecha", s); return; }
        if (s instanceof GiraIzquierda)  { callTurtle1(env, "giraIzquierda", s); return; }
        if (s instanceof PonPos)         { callTurtle2(env, "ponPos", s, s); return; }
        if (s instanceof PonRumbo)       { callTurtle1(env, "ponRumbo", s); return; }
        if (s instanceof Rumbo)          { env.fn.code.add(new IRCall("turtle.rumbo", Collections.<IRValue>emptyList(), null)); return; }
        if (s instanceof PonX)           { callTurtle1(env, "ponX", s); return; }
        if (s instanceof PonY)           { callTurtle1(env, "ponY", s); return; }
        if (s instanceof BajaLapiz)      { env.fn.code.add(new IRCall("turtle.bajaLapiz", Collections.<IRValue>emptyList(), null)); return; }
        if (s instanceof SubeLapiz)      { env.fn.code.add(new IRCall("turtle.subeLapiz", Collections.<IRValue>emptyList(), null)); return; }
        if (s instanceof PonColorLapiz)  { callTurtle1(env, "ponColorLapiz", s); return; }
        if (s instanceof Centro)         { env.fn.code.add(new IRCall("turtle.centro", Collections.<IRValue>emptyList(), null)); return; }
        if (s instanceof Espera)         { callTurtle1(env, "espera", s); return; }
        if (s instanceof OcultaTortuga)  { env.fn.code.add(new IRCall("turtle.oculta", Collections.<IRValue>emptyList(), null)); return; }

        // Fallback
        env.fn.code.add(new IRNop().c("stmt no mapeado: " + s.getClass().getSimpleName()));
    }

    private void callTurtle1(Env env, String op, ASTNode eNodeOwner) {
        ASTNode e = nodeOrFirst(eNodeOwner,
                "getExpr","expr","e","distance","dist","value","arg","argument","node","angle","degrees","xExpr","yExpr");
        IRValue v = genExpr(env, e);
        env.fn.code.add(new IRCall("turtle." + op, Collections.singletonList(v), null));
    }

    private void callTurtle2(Env env, String op, ASTNode owner1, ASTNode owner2) {
        ASTNode e1 = nodeOrFirst(owner1, "getX","x","xExpr","exprX","first","expr","e","arg","argument","node");
        ASTNode e2 = nodeOrFirst(owner2, "getY","y","yExpr","exprY","second","expr","e","arg","argument","node");
        IRValue v1 = genExpr(env, e1), v2 = genExpr(env, e2);
        env.fn.code.add(new IRCall("turtle." + op, Arrays.asList(v1, v2), null));
    }

    // ==========================================================
    //                      EXPRESIONES
    // ==========================================================
    private IRValue genExpr(Env env, ASTNode e) {
        if (e == null) return new IRConst(0);

        // Literales y variables
        if (e instanceof Constant) {
        	Object v = read(e, "getValue","value","val","literal","lit","content","text");

            return new IRConst(v);
        }
        if (e instanceof VarRef) {
            String name = str(e, "getName", "name", "id");
            return env.ensureVar(name);
        }

     // Binarios comunes
        if (e instanceof Addition)       return bin(env, e, IROp.ADD, "getLeft","left","a","e1","first", "getRight","right","b","e2","second");
        if (e instanceof Multiplication) return bin(env, e, IROp.MUL, "getLeft","left","a","e1","first", "getRight","right","b","e2","second");

        // Division puede venir como nodo binario (infijo) o prefijo con campos e1,e2
        if (e instanceof Division) {
            // intentamos primero nombres típicos izquierda/derecha
            ASTNode L = node(e, "getLeft","left","a","e1","first");
            ASTNode R = node(e, "getRight","right","b","e2","second");
            if (L == null || R == null) {
                ASTNode[] two = firstTwoAstChildren(e);
                if (L == null) L = two[0];
                if (R == null) R = two[1];
            }
            IRValue a = genExpr(env, L);
            IRValue b = genExpr(env, R);
            IRTemp t = newTemp();
            env.fn.code.add(new IRBinOpInstr(t, a, b, IROp.DIV));
            return t;
        }

        if (e instanceof Equal)          return bin(env, e, IROp.EQ,  "getLeft","left","a","e1","first", "getRight","right","b","e2","second");
        if (e instanceof GreaterThan)    return bin(env, e, IROp.GT,  "getLeft","left","a","e1","first", "getRight","right","b","e2","second");
        if (e instanceof LessThan)       return bin(env, e, IROp.LT,  "getLeft","left","a","e1","first", "getRight","right","b","e2","second");
        if (e instanceof And)
            return bin(env, e, IROp.AND, "getE1","e1","left","a","first", "getE2","e2","right","b","second");
        if (e instanceof Or)
            return bin(env, e, IROp.OR,  "getE1","e1","left","a","first", "getE2","e2","right","b","second");

        // Exóticos
        if (e instanceof Potencia)
            return bin(env, e, IROp.POW, "getE1","e1","left","a","first", "getE2","e2","right","b","second");
        if (e instanceof Sumaplicacion)  return bin(env, e, IROp.AT,  "getLeft","left","a","e1","first", "getRight","right","b","e2","second");
        if (e instanceof Permutation)    return bin(env, e, IROp.PERM,"getLeft","left","a","e1","first", "getRight","right","b","e2","second");

        // N-arias
        if (e instanceof Suma) {
            List<ASTNode> list = nlist(e, "getList","list","args","values","nodes");
            if (list == null || list.isEmpty()) list = firstAstList(e);
            return reduceN(env, list, IROp.SUM, 0);
        }
        if (e instanceof Producto) {
            List<ASTNode> list = nlist(e, "getFactors","factors","list","nodes","values");
            if (list == null || list.isEmpty()) list = firstAstList(e);
            return reduceN(env, list, IROp.PROD, 1);
        }
        if (e instanceof Diferencia) {
            List<ASTNode> list = nlist(e, "getTerms","terms","list","nodes","values");
            if (list == null || list.isEmpty()) list = firstAstList(e);
            if (list.isEmpty()) {
                IRTemp t = newTemp();
                env.fn.code.add(new IRMove(t, new IRConst(0)));
                return t;
            }
            IRValue acc = genExpr(env, list.get(0));
            for (int i=1;i<list.size();i++){
                IRValue v = genExpr(env, list.get(i));
                IRTemp t = newTemp();
                env.fn.code.add(new IRBinOpInstr(t, acc, v, IROp.DIFF));
                acc = t;
            }
            return acc;
        }

        // Función azar(x)
        if (e instanceof Azar) {
            ASTNode arg = nodeOrFirst(e,
                    "getExpr","expr","expression","exp","e",
                    "value","val","valueExpr","argExpr",
                    "arg","argument","x","n","node");
            IRValue v = genExpr(env, arg);
            IRTemp t = newTemp();
            env.fn.code.add(new IRCall("runtime.azar",
                    java.util.Collections.singletonList(v), t).c("random/azar"));
            return t;
        }



        // Desconocido -> 0 con NOP
        IRTemp t = newTemp();
        env.fn.code.add(new IRNop().c("expr no mapeada: " + e.getClass().getSimpleName()));
        env.fn.code.add(new IRMove(t, new IRConst(0)));
        return t;
    }

    private IRValue bin(Env env, ASTNode e, IROp op,
            String l1, String l2, String l3, String l4, String l5,
            String r1, String r2, String r3, String r4, String r5) {
ASTNode L = node(e, l1,l2,l3,l4,l5);
ASTNode R = node(e, r1,r2,r3,r4,r5);

// Fallback: si no logramos L o R, toma los dos primeros campos ASTNode
if (L == null || R == null) {
ASTNode[] two = firstTwoAstChildren(e);
if (L == null && two[0] != null) L = two[0];
if (R == null && two[1] != null) R = two[1];
}

IRValue a = genExpr(env, L);
IRValue b = genExpr(env, R);
IRTemp t = newTemp();
env.fn.code.add(new IRBinOpInstr(t, a, b, op));
return t;
}

    private IRValue reduceN(Env env, List<ASTNode> list, IROp op, int neutral) {
        if (list == null || list.isEmpty()) {
            // Fallback: localizar una List<ASTNode> en el nodo que llamó a reduceN
            // Para eso, examinamos el stack: el llamador inmediato es genExpr(...)
            // más simple: si list es null/empty, no podemos saber el "owner" aquí,
            // así que devolvemos el neutro.
            return new IRConst(neutral);
        }
        IRValue acc = genExpr(env, list.get(0));
        for (int i=1;i<list.size();i++){
            IRValue v = genExpr(env, list.get(i));
            IRTemp t = newTemp();
            env.fn.code.add(new IRBinOpInstr(t, acc, v, op));
            acc = t;
        }
        return acc;
    }

    // ==========================================================
    //                REFLECTION HELPERS (robustos)
    // ==========================================================
    private static Object read(Object o, String... names) {
        if (o == null) return null;
        Class<?> c = o.getClass();
        for (String n : names) {
            // 1) método exacto
            Method m = findZeroArg(c, n);
            if (m != null) return invoke(m, o);

            // 2) getter estándar
            String gn = n;
            if (!n.startsWith("get")) {
                gn = "get" + Character.toUpperCase(n.charAt(0)) + n.substring(1);
            }
            Method gm = findZeroArg(c, gn);
            if (gm != null) return invoke(gm, o);

            // 3) campo directo
            Field f = findField(c, n);
            if (f != null) return get(f, o);
        }
        return null;
    }

    private static Method findZeroArg(Class<?> c, String name) {
        Class<?> k = c;
        while (k != null) {
            for (Method m : k.getDeclaredMethods()) {
                if (m.getName().equals(name) && m.getParameterTypes().length == 0) {
                    m.setAccessible(true);
                    return m;
                }
            }
            k = k.getSuperclass();
        }
        return null;
    }

    private static Field findField(Class<?> c, String name) {
        Class<?> k = c;
        while (k != null) {
            try {
                Field f = k.getDeclaredField(name);
                f.setAccessible(true);
                return f;
            } catch (NoSuchFieldException ignored) {}
            k = k.getSuperclass();
        }
        return null;
    }

    private static Object invoke(Method m, Object o) {
        try { return m.invoke(o); } catch (Exception e) { return null; }
    }
    private static Object get(Field f, Object o) {
        try { return f.get(o); } catch (Exception e) { return null; }
    }

    @SuppressWarnings("unchecked")
    private static List<ASTNode> nlist(Object o, String... names) {
        Object v = read(o, names);
        if (v instanceof List) return (List<ASTNode>) v;
        return Collections.emptyList();
    }
    private static ASTNode node(Object o, String... names) {
        Object v = read(o, names);
        return (v instanceof ASTNode) ? (ASTNode) v : null;
    }
    @SuppressWarnings("unchecked")
    private static List<?> list(Object o, String... names) {
        Object v = read(o, names);
        return (v instanceof List) ? (List<?>) v : null;
    }
    private static String str(Object o, String... names) {
        Object v = read(o, names);
        return (v == null) ? null : String.valueOf(v);
    }
    private static ASTNode firstAstChild(Object o) {
        if (o == null) return null;
        Class<?> k = o.getClass();
        while (k != null) {
            Field[] fs = k.getDeclaredFields();
            for (Field f : fs) {
                try {
                    if (ASTNode.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        Object v = f.get(o);
                        if (v instanceof ASTNode) return (ASTNode) v;
                    }
                } catch (Exception ignored) {}
            }
            k = k.getSuperclass();
        }
        return null;
    }
    
    private static ASTNode[] firstTwoAstChildren(Object o) {
        ASTNode[] out = new ASTNode[2];
        if (o == null) return out;
        Class<?> k = o.getClass();
        while (k != null) {
            Field[] fs = k.getDeclaredFields();
            for (Field f : fs) {
                try {
                    if (ASTNode.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        Object v = f.get(o);
                        if (v instanceof ASTNode) {
                            if (out[0] == null) out[0] = (ASTNode) v;
                            else if (out[1] == null) { out[1] = (ASTNode) v; return out; }
                        }
                    }
                } catch (Exception ignored) {}
            }
            k = k.getSuperclass();
        }
        return out;
    }
    
 // Devuelve el ASTNode pedido por nombre o, si no existe, el primer campo ASTNode encontrado.
    private static ASTNode nodeOrFirst(Object o, String... names) {
        ASTNode n = node(o, names);
        if (n != null) return n;

        if (o == null) return null;
        Class<?> k = o.getClass();
        while (k != null) {
            for (Field f : k.getDeclaredFields()) {
                try {
                    f.setAccessible(true);
                    Object v = f.get(o);
                    if (v instanceof ASTNode) return (ASTNode) v;
                } catch (Exception ignored) {}
            }
            k = k.getSuperclass();
        }
        return null;
    }

    
    
    @SuppressWarnings("unchecked")
    private static List<ASTNode> firstAstList(Object o) {
        if (o == null) return Collections.emptyList();
        Class<?> k = o.getClass();
        while (k != null) {
            Field[] fs = k.getDeclaredFields();
            for (Field f : fs) {
                try {
                    f.setAccessible(true);
                    Object v = f.get(o);
                    if (v instanceof List) {
                        List<?> L = (List<?>) v;
                        if (!L.isEmpty() && L.get(0) instanceof ASTNode) {
                            return (List<ASTNode>) L;
                        }
                    }
                } catch (Exception ignored) {}
            }
            k = k.getSuperclass();
        }
        return Collections.emptyList();
    }



}
