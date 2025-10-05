package com.tallerantlr.simple.interprete.ast;

import java.util.Map;

public class VarDecl implements ASTNode {
	
	private String name;
	

	public VarDecl(String name) {
		super();
		this.name = name;
	}


	@Override
	public Object execute(Map<String, Object> symbolTable) {
	    // variable declarada pero sin valor/tipo aún
	    symbolTable.put(name, null);
	    return null;
	}

}
