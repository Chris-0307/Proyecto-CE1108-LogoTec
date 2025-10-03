package com.tallerantlr.simple.interprete.ide;

@FunctionalInterface
public interface SyntaxErrorHandler {
    void onError(int line, int charPos, String msg);
}
