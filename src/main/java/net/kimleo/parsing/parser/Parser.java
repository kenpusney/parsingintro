package net.kimleo.parsing.parser;

import net.kimleo.parsing.ast.Expression;
import net.kimleo.parsing.ast.Node;
import net.kimleo.parsing.ast.NumberLiteral;
import net.kimleo.parsing.lexer.Token;
import net.kimleo.parsing.lexer.TokenType;

import java.util.List;

import static net.kimleo.parsing.lexer.TokenType.RIGHT_PAREN;

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
        Node left = term();
        if (accept(TokenType.PLUS) || accept(TokenType.MINUS)) {
            rewind(1);
            char operator = operator();
            Node right = term();
            return new Expression(operator, left, right);
        }
        return left;
    }

    private Node term() {
        Node left = factor();
        if (accept(TokenType.TIMES) || accept(TokenType.DIVIDE)) {
            rewind(1);
            char operator = operator();
            Node right = factor();
            return new Expression(operator, left, right);
        }
        return left;
    }

    private Node factor() {
        if (accept(TokenType.LEFT_PAREN)) {
            Node expr = expression();
            expect(RIGHT_PAREN);
            return expr;
        } else {
            return number();
        }
    }

    private void expect(TokenType tokenType) {
        if (!accept(tokenType)) {
            throw unexpected(current());
        }
    }

    private char operator() {
        Token token = current();
        next(1);
        return (char) token.value;
    }

    private void rewind(int step) {
        index -= step;
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
        if (!EOF() && current().tokenType == tokenType) {
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
