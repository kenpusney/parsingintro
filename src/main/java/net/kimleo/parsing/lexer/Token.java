package net.kimleo.parsing.lexer;

public class Token {
    public final TokenType tokenType;
    public final Object value;

    public Token(TokenType tokenType) {
        this(tokenType, null);
    }

    public Token(TokenType tokenType, Object value) {
        this.tokenType = tokenType;
        this.value = value;
    }
}
