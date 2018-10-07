import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.Console;
import java.io.IOException;
import java.nio.charset.Charset;

public class ChatApp {
    public static void main(String[] args) throws IOException {
        System.out.println("** Expression Eval w/ antlr-listener **");
        Console c = System.console();
        if (c == null) {
            System.err.println("No Console");
            System.exit(1);
        }
        CharStream input = CharStreams.fromPath("/home/saulocalixto/IdeaProjects/metodo/src/main/resources/chat.g4", "Teste");;
        // Get lexer
        ChatLexer lexer = new ChatLexer(input);
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Pass tokens to parser
        ChatParser parser = new ChatParser(tokens);
        // Walk parse-tree and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        ChatBaseListener listener = new ChatBaseListener();

        // walk from the root of parse tree
        walker.walk(listener, parser.message());
    }
}