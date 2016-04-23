package net.kimleo.parsing.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Lexer {
    private static final List<Character> OPERATORS = Arrays.asList('+', '-', '*', '/');
    private static final HashMap<Character, TokenType> OPERATOR_TYPES = new HashMap<Character, TokenType>() {{
        put('+', TokenType.PLUS);
        put('-', TokenType.MINUS);
        put('*', TokenType.TIMES);
        put('/', TokenType.DIVIDE);
    }};
    private final String source;
    private int index = 0;

    public Lexer(String source) {
        this.source = source;
    }

    public List<Token> run() {
        List<Token> tokens = new ArrayList<>();
        while (!EOF()) {
            tokens.add(nextToken());
        }
        return tokens;
    }

    private Token nextToken() {
        while (!EOF()) {
            if (Character.isDigit(current())) {
                String number = nextNumber();
                return number(Integer.parseInt(number));
            } else if (isOperator(current())) {
                char operator = current();
                next(1);
                return operator(operator);
            } else if (Character.isSpaceChar(current())) {
                next(1);
                continue;
            }
            throw unknown(current());
        }
        throw new IllegalStateException("end of source");
    }

    public static Token operator(char operator) {
        return new Token(OPERATOR_TYPES.get(operator));
    }

    public static Token number(int value) {
        return new Token(TokenType.NUMBER, value);
    }

    private boolean isOperator(char current) {
        return OPERATORS.contains(current);
    }

    private LexerException unknown(char character) {
        return new LexerException("Unknown character: " + character);
    }

    private String nextNumber() {
        StringBuilder sb = new StringBuilder();
        while (!EOF() && Character.isDigit(current())) {
            sb.append(current());
            next(1);
        }
        return sb.toString();
    }

    private void next(int i) {
        index += i;
    }

    private boolean EOF() {
        return index >= source.length();
    }

    private char current() {
        if (!EOF()) return source.charAt(index);
        throw new IllegalStateException("end of source");
    }
}
