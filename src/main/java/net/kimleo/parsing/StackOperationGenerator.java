package net.kimleo.parsing;

import net.kimleo.parsing.ast.*;

import java.util.ArrayList;
import java.util.List;

public class StackOperationGenerator implements Visitor<String> {

    public final List<String> operations = new ArrayList<>();

    @Override
    public String visit(Node node) {
        return node.accept(this);
    }

    @Override
    public String visit(NumberLiteral num) {
        operations.add("push "+num.value);

        return Integer.toString(num.value);
    }

    @Override
    public String visit(Expression expr) {
        expr.left.accept(this);
        expr.right.accept(this);

        String operation;
        switch (expr.operator) {
            case '+':
                operation = "add";
                break;
            case '-':
                operation = "sub";
                break;
            case '*':
                operation = "multiply";
                break;
            case '/':
                operation = "divide";
                break;
            default:
                throw new UnsupportedOperationException();
        }

        operations.add(operation);

        return operation;
    }

    @Override
    public String visit(Identifier id) {
        return id.name;
    }

    @Override
    public String visit(FunCall funCall) {

        for (Node node : funCall.argument) {
            node.accept(this);
        }

        String fnName = funCall.name.accept(this);
        operations.add(String.format("call %s, %d", fnName, funCall.argument.size()));
        return fnName;
    }
}
