package net.kimleo.parsing.ast;

public interface Visitor<T> {
    T visit(Node node);
    T visit(NumberLiteral num);
    T visit(Expression expr);
    T visit(Identifier id);
    T visit(FunCall funCall);
}
