package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class VarAssign implements ASTNode {
	
	private String name;
	private ASTNode expression;
	
	

	public VarAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}



	@Override
	public Object execute(Map<String, Object> symbolTable) {
	    Object newVal = expression.execute(symbolTable);
	    Object oldVal = symbolTable.get(name);

	    if (!symbolTable.containsKey(name)) {
	        throw new RuntimeException("Error semántico: variable no declarada: " + name);
	    }

	    if (oldVal == null) { // primera asignación fija el tipo
	        symbolTable.put(name, newVal);
	        return null;
	    }

	    if (newVal == null || !oldVal.getClass().equals(newVal.getClass())) {
	        String tOld = oldVal.getClass().getSimpleName();
	        String tNew = (newVal == null) ? "null" : newVal.getClass().getSimpleName();
	        throw new RuntimeException("Error semántico: variable '" + name +
	                                   "' es de tipo " + tOld + " y se intentó asignar " + tNew);
	    }

	    symbolTable.put(name, newVal);
	    return null;
	}

}
