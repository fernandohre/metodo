package com.github.saulocalixto.calculadoraantlr;

import com.github.saulocalixto.calculadoraantlr.antlr.calculadoraLexer;
import com.github.saulocalixto.calculadoraantlr.antlr.calculadoraParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Principal {
    private static ParseTree parse(String programa) {
        final ANTLRInputStream input = new ANTLRInputStream(programa);
        final calculadoraLexer lexer = new calculadoraLexer(input);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final calculadoraParser parser = new calculadoraParser(tokens);
        return parser.prog();
    }

    public static void main(String... args) throws IOException {
        String programa = "1+2*(3-4)*5/2";
        if(args.length > 0) {
            programa = args[0];
        }

        final ParseTree tree = parse(programa);
        final ParseTreeWalker walker = new ParseTreeWalker();

        // Percorrendo a árvore para avaliar o programa
        final MinhaCalculadora listener = new MinhaCalculadora();
        walker.walk(listener, tree);
        int resultado = listener.resultado();

        System.out.println( programa);
        System.out.println("========");
        System.out.println(resultado);
        System.out.println("========");
    }
}
