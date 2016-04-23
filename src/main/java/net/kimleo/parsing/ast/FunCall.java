package net.kimleo.parsing.ast;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class FunCall implements Node {

    public final Node name;
    public final List<Node> argument;

    public FunCall(Node name, List<Node> argument) {
        this.name = name;
        this.argument = argument;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunCall funCall = (FunCall) o;

        if (name != null ? !name.equals(funCall.name) : funCall.name != null) return false;
        return argument != null ? argument.equals(funCall.argument) : funCall.argument == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (argument != null ? argument.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String args = argument.stream().map(Object::toString).collect(Collectors.joining(", "));
        return String.format("%s(%s)", name, args);
    }
}
