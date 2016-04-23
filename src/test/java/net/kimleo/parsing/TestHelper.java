package net.kimleo.parsing;

import net.kimleo.parsing.ast.Node;
import net.kimleo.parsing.lexer.Lexer;
import net.kimleo.parsing.lexer.Token;
import net.kimleo.parsing.parser.Parser;

import java.util.List;

public class TestHelper {
    public static List<Token> lex(String source) {
        return new Lexer(source).run();
    }

    public static Node parse(String source) {
        return new Parser(lex(source)).run();
    }

    public static Integer interpret(String source) {
        return new Parser(lex(source)).run().accept(new SimpleInterpreter());
    }

    public static SSAGenerator generate(String source) {
        SSAGenerator generator = new SSAGenerator();
        parse(source).accept(generator);
        return generator;
    }
}
