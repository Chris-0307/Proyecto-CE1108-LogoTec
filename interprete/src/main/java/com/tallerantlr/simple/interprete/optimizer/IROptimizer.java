package com.tallerantlr.simple.interprete.optimizer;

import com.tallerantlr.simple.interprete.ir.IR.*;
import java.util.*;

public final class IROptimizer {
    private IROptimizer() {}

    public static void run(IRModule m) {
        for (IRFunction f : m.functions.values()) {
            foldConstants(f);
            removeNops(f);
        }
    }

    // Plegado de constantes para binarios/unarios simples
    private static void foldConstants(IRFunction f) {
        List<IRInstr> code = f.code;
        for (int i = 0; i < code.size(); i++) {
            IRInstr ins = code.get(i);
            if (ins instanceof IRBinOpInstr) {
                IRBinOpInstr b = (IRBinOpInstr) ins;
                if (b.a instanceof IRConst && b.b instanceof IRConst) {
                    Object val = evalBin((IRConst)b.a, (IRConst)b.b, b.op);
                    if (val != null) code.set(i, new IRMove(b.dst, new IRConst(val)));
                }
            } else if (ins instanceof IRUnaryOpInstr) {
                IRUnaryOpInstr u = (IRUnaryOpInstr) ins;
                if (u.v instanceof IRConst) {
                    Object val = evalUnary((IRConst)u.v, u.op);
                    if (val != null) code.set(i, new IRMove(u.dst, new IRConst(val)));
                }
            }
        }
    }

    private static Object evalBin(IRConst A, IRConst B, IROp op) {
        Object a = A.value, b = B.value;
        switch (op) {
            case ADD: return ((Number)a).intValue() + ((Number)b).intValue();
            case SUB: return ((Number)a).intValue() - ((Number)b).intValue();
            case MUL: return ((Number)a).intValue() * ((Number)b).intValue();
            case DIV: return ((Number)b).intValue() == 0 ? null
                          : ((Number)a).intValue() / ((Number)b).intValue();
            case POW: return (int)Math.pow(((Number)a).intValue(), ((Number)b).intValue());
            case EQ:  return a.equals(b);
            case LT:  return ((Number)a).intValue() <  ((Number)b).intValue();
            case GT:  return ((Number)a).intValue() >  ((Number)b).intValue();
            case AND: return ((Boolean)a) && ((Boolean)b);
            case OR:  return ((Boolean)a) || ((Boolean)b);
            case SUM: return ((Number)a).intValue() + ((Number)b).intValue();   // n-arias ya se reducen en CodeGen
            case PROD:return ((Number)a).intValue() * ((Number)b).intValue();
            case DIFF:return ((Number)a).intValue() - ((Number)b).intValue();
            default:  return null;
        }
    }

    private static Object evalUnary(IRConst V, IROp op) {
        Object v = V.value;
        switch (op) {
            case NOT: return !((Boolean)v);
            case NEG: return -((Number)v).intValue();
            default:  return null;
        }
    }

    private static void removeNops(IRFunction f) {
        f.code.removeIf(ir -> ir instanceof IRNop);
    }
}
