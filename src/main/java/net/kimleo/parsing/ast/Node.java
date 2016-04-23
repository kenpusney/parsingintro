package net.kimleo.parsing.ast;

public interface Node {
    <T> T accept(Visitor<T> visitor);
}
