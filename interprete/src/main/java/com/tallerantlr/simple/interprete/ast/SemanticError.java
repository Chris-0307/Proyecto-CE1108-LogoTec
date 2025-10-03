package com.tallerantlr.simple.interprete.ast;

public class SemanticError extends RuntimeException {
    private final int line;
    private final int charPos;

    public SemanticError(String message, int line, int charPos) {
        super(message);
        this.line = line;
        this.charPos = charPos;
    }

    public int getLine() { return line; }
    public int getCharPos() { return charPos; }
}
