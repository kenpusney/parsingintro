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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (tokenType != token.tokenType) return false;
        return value != null ? value.equals(token.value) : token.value == null;

    }

    @Override
    public int hashCode() {
        int result = tokenType != null ? tokenType.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s<%s>", tokenType, value == null ? "" : value.toString());
    }
}
