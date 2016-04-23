package net.kimleo.parsing.lexer;

import java.util.List;

public class TestHelper {
    public static List<Token> lex(String source) {
        return new Lexer(source).run();
    }
}
