package com.tallerantlr.simple.interprete;

import com.tallerantlr.simple.interprete.ast.ASTNode;
import com.tallerantlr.simple.interprete.ast.ProcedureDef;
import com.tallerantlr.simple.interprete.codegen.CodeGenerator;
import com.tallerantlr.simple.interprete.ir.IR.*;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CodegenRunner {

    private static final String EXTENSION = "smp";
    private static final String DIRBASE   = "src/test/resources/";

    public static void main(String[] args) throws IOException {
        String[] files = (args.length == 0) ? new String[]{"test." + EXTENSION} : args;

        for (String file : files) {
            Path path = Paths.get(DIRBASE + file);
            System.out.println("== Generando IR de: " + path);

            CharStream in = CharStreams.fromPath(path);
            SimpleLexer lexer = new SimpleLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SimpleParser parser = new SimpleParser(tokens);

            parser.executeOnParse = false;
            parser.enforceVarDecl = false;

            SimpleParser.ProgramContext tree = parser.program();

            @SuppressWarnings("unchecked")
            List<ASTNode> body = (List<ASTNode>) tree.body;

            Map<String, ProcedureDef> procTable = parser.getProcTable();
            Set<String> globals = parser.getGlobalNames();
            CodeGenerator cg = new CodeGenerator(globals);
            cg.generateProgram(body, procTable);
            IRModule mod = cg.getModule();

            Path out = Paths.get(DIRBASE + file.replace("." + EXTENSION, ".ir.txt"));
            Files.write(out, mod.toString().getBytes("UTF-8"));
            System.out.println(">> IR escrito en: " + out.toAbsolutePath());
            System.out.println();
        }
    }
}
