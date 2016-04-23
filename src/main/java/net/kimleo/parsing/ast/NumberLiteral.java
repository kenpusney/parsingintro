package net.kimleo.parsing.ast;

public class NumberLiteral implements Node {
    public final int value;

    public NumberLiteral(int value) {
        this.value = value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberLiteral that = (NumberLiteral) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("num(%d)", value);
    }
}
