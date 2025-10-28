package com.tallerantlr.simple.interprete.ir;

import java.util.*;

/**
 * IR (Intermediate Representation) de tres direcciones (TAC).
 * - Soporta funciones (incluye "main")
 * - Bloques lineales con etiquetas, GOTO, IF_GOTO
 * - Operaciones binarias y unarias
 * - CALL con retorno opcional
 * - PRINTLN como intrínseco (o se puede modelar como CALL)
 * - Comentarios, NOP
 *
 * Emisión textual vía IRModule.toString()
 */
public final class IR {

    // ===== Valores =====
    public interface IRValue {
        String render();
    }

    public static final class IRConst implements IRValue {
        public final Object value; // Integer, Boolean, String
        public IRConst(Object v) { this.value = v; }
        @Override public String render() {
            if (value instanceof String) {
                String s = ((String) value).replace("\\", "\\\\").replace("\"", "\\\"");
                return "\"" + s + "\"";
            }
            return String.valueOf(value);
        }
    }

    public static final class IRVar implements IRValue {
        public final String name; // variable (local o global)
        public IRVar(String name) { this.name = name; }
        @Override public String render() { return name; }
    }

    public static final class IRTemp implements IRValue {
        public final String name; // t1, t2, ...
        public IRTemp(String n) { this.name = n; }
        @Override public String render() { return name; }
    }

    public static final class IRLabel implements IRValue {
        public final String name; // L1, Lcond...
        public IRLabel(String n) { this.name = n; }
        @Override public String render() { return name + ":"; }
    }

    // ===== Operaciones =====
    public enum IROp {
        // aritmética
        ADD, SUB, MUL, DIV, MOD, POW,
        // lógicas/booleanas
        AND, OR, NOT,
        // comparaciones: devuelven 0/1
        EQ, LT, GT, LE, GE,
        // "exóticas" del lenguaje
        AT, PERM, SUM, PROD, DIFF,
        // unario
        NEG
    }

    // ===== Instrucciones =====
    public static abstract class IRInstr {
        public String comment;
        public IRInstr c(String c) { this.comment = c; return this; }
        protected String maybeComment() { return (comment == null || comment.isEmpty()) ? "" : "    # " + comment; }
        public abstract String render();
    }

    public static final class IRMove extends IRInstr {
        public final IRValue dst, src;
        public IRMove(IRValue dst, IRValue src){ this.dst = dst; this.src = src; }
        @Override public String render() {
            return String.format("%s = %s%s", dst.render(), src.render(), (comment == null?"":"    # "+comment));
        }
    }

    public static final class IRBinOpInstr extends IRInstr {
        public final IRValue dst, a, b;
        public final IROp op;
        public IRBinOpInstr(IRValue dst, IRValue a, IRValue b, IROp op){
            this.dst = dst; this.a = a; this.b = b; this.op = op;
        }
        @Override public String render() {
            return String.format("%s = %s %s %s%s", dst.render(), a.render(), op.name().toLowerCase(), b.render(), (comment==null?"":"    # "+comment));
        }
    }

    public static final class IRUnaryOpInstr extends IRInstr {
        public final IRValue dst, v;
        public final IROp op; // NOT o NEG
        public IRUnaryOpInstr(IRValue dst, IRValue v, IROp op){ this.dst = dst; this.v = v; this.op = op; }
        @Override public String render() {
            return String.format("%s = %s %s%s", dst.render(), op.name().toLowerCase(), v.render(), (comment==null?"":"    # "+comment));
        }
    }

    public static final class IRLabelInstr extends IRInstr {
        public final IRLabel label;
        public IRLabelInstr(IRLabel l){ this.label = l; }
        @Override public String render() { return label.render(); }
    }

    public static final class IRGoto extends IRInstr {
        public final IRLabel target;
        public IRGoto(IRLabel t){ this.target = t; }
        @Override public String render() { return "goto " + target.name; }
    }

    public static final class IRIfGoto extends IRInstr {
        public final IRValue cond; // !=0 salta
        public final IRLabel target;
        public IRIfGoto(IRValue cond, IRLabel t){ this.cond = cond; this.target = t; }
        @Override public String render() { return "if_true " + cond.render() + " goto " + target.name; }
    }

    public static final class IRReturn extends IRInstr {
        public final IRValue value; // puede ser null
        public IRReturn(IRValue v){ this.value = v; }
        @Override public String render() { return value==null ? "ret" : ("ret " + value.render()); }
    }

    public static final class IRCall extends IRInstr {
        public final String callee;
        public final List<IRValue> args;
        public final IRValue dst; // null para void
        public IRCall(String callee, List<IRValue> args, IRValue dst){
            this.callee = callee; this.args = args; this.dst = dst;
        }
        @Override public String render() {
            StringBuilder sb = new StringBuilder();
            if (dst != null) sb.append(dst.render()).append(" = ");
            sb.append("call ").append(callee).append("(");
            for (int i=0;i<args.size();i++){
                if (i>0) sb.append(", ");
                sb.append(args.get(i).render());
            }
            sb.append(")");
            if (comment != null) sb.append("    # ").append(comment);
            return sb.toString();
        }
    }

    public static final class IRPrintln extends IRInstr {
        public final IRValue value;
        public IRPrintln(IRValue v){ this.value = v; }
        @Override public String render() { return "println " + value.render(); }
    }

    public static final class IRNop extends IRInstr {
        @Override public String render() { return "nop" + (comment==null?"":"    # "+comment); }
    }

    // ===== Función / Módulo =====
    public static final class IRFunction {
        public final String name;
        public final List<String> params = new ArrayList<>();
        public final Set<String> locals = new LinkedHashSet<>();
        public final List<IRInstr> code = new ArrayList<>();
        public IRFunction(String name){ this.name = name; }
        public void addLocal(String v){ locals.add(v); }
        public String render() {
            StringBuilder sb = new StringBuilder();
            sb.append(".fn ").append(name).append("(params=").append(params.size()).append(")\n");
            if (!locals.isEmpty()) {
                sb.append("  .locals: ").append(String.join(", ", locals)).append("\n");
            }
            for (IRInstr ins: code) sb.append("  ").append(ins.render()).append("\n");
            sb.append(".endfn\n");
            return sb.toString();
        }
    }

    public static final class IRModule {
        public final Map<String, IRFunction> functions = new LinkedHashMap<>();
        public final Set<String> globals = new LinkedHashSet<>();
        public IRFunction newFunction(String name){
            IRFunction f = new IRFunction(name);
            functions.put(name, f);
            return f;
        }
        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            if (!globals.isEmpty()){
                sb.append(".globals: ").append(String.join(", ", globals)).append("\n\n");
            }
            for (IRFunction f: functions.values()) {
                sb.append(f.render()).append("\n");
            }
            return sb.toString();
        }
    }
}
