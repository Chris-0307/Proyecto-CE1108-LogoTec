package com.tallerantlr.simple.interprete.ide;

import com.tallerantlr.simple.interprete.SimpleLexer;
import com.tallerantlr.simple.interprete.SimpleParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.function.Consumer;

public class InterpreterRunner {

    public static void runFromString(String source,
                                     Consumer<String> out,
                                     Consumer<String> err) throws IOException
    {
        // Validación: primera línea debe ser comentario (como en tu Main original)
        try (BufferedReader br = new BufferedReader(new StringReader(source))) {
            String first = br.readLine();
            if (first == null || !first.trim().startsWith("//")) {
                throw new RuntimeException("Error: el código debe iniciar con un comentario en la primera línea.");
            }
        }
        // También al menos un comentario en todo el código
        boolean hasAnyComment = false;
        try (BufferedReader br = new BufferedReader(new StringReader(source))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("//")) { hasAnyComment = true; break; }
            }
        }
        if (!hasAnyComment) {
            throw new RuntimeException("Error: debe existir al menos un comentario en el programa.");
        }

        CharStream in = CharStreams.fromString(source);
        SimpleLexer lexer = new SimpleLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);

        // Listener de errores “bonito”
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                              int line, int charPositionInLine,
                                              String msg, RecognitionException e) {
                err.accept(String.format("Error de sintaxis en %d:%d - %s", line, charPositionInLine, msg));
                throw new RuntimeException("Abortado por error de sintaxis");
            }
        });

        // Entrypoint: program (tu acción en 'program' ejecuta el body)
        parser.program();
    }
    
    public static void parseOnly(String source,
            ParseTreePanel targetPanel,
            Consumer<String> err) throws IOException
{
// Para el árbol NO exigimos el comentario/ni variable mínima
CharStream in = CharStreams.fromString(source);
SimpleLexer lexer = new SimpleLexer(in);
CommonTokenStream tokens = new CommonTokenStream(lexer);
SimpleParser parser = new SimpleParser(tokens);

// No ejecutar acciones ni validar "al menos una var"
parser.executeOnParse = false;
parser.enforceVarDecl = false;

parser.removeErrorListeners();
parser.addErrorListener(new BaseErrorListener() {
@Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                         int line, int charPositionInLine,
                         String msg, RecognitionException e) {
err.accept(String.format("Error de sintaxis en %d:%d - %s", line, charPositionInLine, msg));
// seguimos para mostrar árbol parcial, o lanza RuntimeException si prefieres abortar
}
});

ParseTree tree = parser.program();
targetPanel.setTree(parser, tree);
}
}
