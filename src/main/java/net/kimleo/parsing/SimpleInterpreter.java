package net.kimleo.parsing;

import net.kimleo.parsing.ast.Expression;
import net.kimleo.parsing.ast.Node;
import net.kimleo.parsing.ast.NumberLiteral;
import net.kimleo.parsing.ast.Visitor;

public class SimpleInterpreter implements Visitor<Integer> {
    @Override
    public Integer visit(Node node) {
        return node.accept(this);
    }

    @Override
    public Integer visit(NumberLiteral num) {
        return num.value;
    }

    @Override
    public Integer visit(Expression expr) {
        Integer left = expr.left.accept(this);
        Integer right = expr.right.accept(this);

        switch (expr.operator) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
            case '/': return left / right;
            default:
                throw new UnsupportedOperationException("Unknown operator " + expr.operator);
        }
    }
}
