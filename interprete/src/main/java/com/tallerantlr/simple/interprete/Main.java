package com.tallerantlr.simple.interprete;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
import org.antlr.v4.runtime.*;

public class Main {
    private static final String EXTENSION = "smp";
    private static final String DIRBASE = "src/test/resources/";

    private static void validateFirstLineIsComment(Path path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String first = br.readLine();
            if (first == null || !first.trim().startsWith("//")) {
                throw new RuntimeException("Error: el código debe iniciar con un comentario en la primera línea.");
            }
        }
        // También puedes validar “al menos un comentario en todo el archivo”:
        long comments = Files.lines(path).filter(l -> l.trim().startsWith("//")).count();
        if (comments == 0) {
            throw new RuntimeException("Error: debe existir al menos un comentario en el programa.");
        }
    }

    public static void main(String[] args) throws IOException {
        String[] files = (args.length == 0) ? new String[]{ "test." + EXTENSION } : args;
        System.out.println("Dirbase: " + DIRBASE);
        for (String file : files){
            System.out.println("START: " + file);

            Path path = Paths.get(DIRBASE + file);
            validateFirstLineIsComment(path);

            CharStream in = CharStreams.fromPath(path);
            SimpleLexer lexer = new SimpleLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);

            // <<< CAMBIO CLAVE: usar la regla de entrada 'program'
            SimpleParser.ProgramContext tree = parser.program();

            // El propio 'program' ejecuta el body con acciones Java
            // (si prefieres Visitor, aquí instancias y visitas)
            System.out.println("FINISH: " + file);
        }
    }
}
