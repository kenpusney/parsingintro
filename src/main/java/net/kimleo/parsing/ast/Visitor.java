package net.kimleo.parsing.ast;

public interface Visitor<T> {
    T visit(Node node);
}
