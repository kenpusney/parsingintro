package net.kimleo.parsing.parser;

import net.kimleo.parsing.ast.Node;
import net.kimleo.parsing.ast.NumberLiteral;
import net.kimleo.parsing.lexer.Token;
import net.kimleo.parsing.lexer.TokenType;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int index = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Node run() {
        return expression();
    }

    private Node expression() {
        return number();
    }

    private Node number() {
        Token token = current();
        if(accept(TokenType.NUMBER)) {
            return new NumberLiteral((Integer) token.value);
        }
        throw unexpected(token);
    }

    private Token current() {
        if (!EOF()) return tokens.get(index);
        throw new IllegalStateException("end of token list");
    }

    private boolean accept(TokenType tokenType) {
        if (current().tokenType == tokenType) {
            next(1);
            return true;
        } else {
            return false;
        }
    }

    private void next(int step) {
        index += step;
    }

    private boolean EOF() {
        return index >= tokens.size();
    }

    private ParserException unexpected(Token token) {
        return new ParserException("Unexpected token " + token.tokenType);
    }

}
