package com.tallerantlr.simple.interprete.backend;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class LLVMToolchain {

    public static class Result {
        public final int code;
        public final String out, err;
        public Result(int c, String o, String e){ code=c; out=o; err=e; }
    }

    public static void writeFile(Path p, String content) throws IOException {
        Files.createDirectories(p.getParent());
        Files.write(p, content.getBytes(StandardCharsets.UTF_8));
    }

    public static Result run(String... cmd) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(false);
        Process pr = pb.start();
        String out = read(pr.getInputStream());
        String err = read(pr.getErrorStream());
        int rc = pr.waitFor();
        return new Result(rc, out, err);
    }

    private static String read(InputStream in) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line; while ((line = br.readLine()) != null) sb.append(line).append('\n');
            return sb.toString();
        }
    }

    /**
     * Pipeline:
     *  - opt -O2 in.ll -o out.bc
     *  - llc -filetype=obj out.bc -o out.o
     *  - clang out.o runtime.o -o a.out
     */
    public static void buildExecutable(Path ll, Path runtimeObj, Path exeOut) throws Exception {
        Path bc = ll.resolveSibling(changeExt(ll.getFileName().toString(), ".bc"));
        Path obj = ll.resolveSibling(changeExt(ll.getFileName().toString(), ".o"));

        mustOk(run("opt", "-O2", "-o", bc.toString(), ll.toString()), "opt");
        mustOk(run("llc", "-filetype=obj", "-o", obj.toString(), bc.toString()), "llc");
        mustOk(run("clang", "-o", exeOut.toString(), obj.toString(), runtimeObj.toString()), "clang");
    }

    private static void mustOk(Result r, String step) {
        if (r.code != 0) throw new RuntimeException("LLVM step "+step+" failed:\nSTDOUT:\n"+r.out+"\nSTDERR:\n"+r.err);
    }

    private static String changeExt(String name, String newExt) {
        int i = name.lastIndexOf('.');
        return (i>=0? name.substring(0,i):name) + newExt;
    }
}
