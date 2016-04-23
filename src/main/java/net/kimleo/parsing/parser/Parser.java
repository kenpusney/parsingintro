package net.kimleo.parsing.parser;

import net.kimleo.parsing.ast.Node;
import net.kimleo.parsing.lexer.Token;

import java.util.List;

public class Parser {
    private final List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Node run() {
        throw new UnsupportedOperationException();
    }
}
