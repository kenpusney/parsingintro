package net.kimleo.parsing;

import net.kimleo.parsing.ast.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class SimpleInterpreter implements Visitor<Integer> {
    private static final HashMap<String, Function<List<Integer>, Integer>> FUNCTIONS =
            new HashMap<String, Function<List<Integer>, Integer>>() {{
                put("max", (ints) -> ints.stream().max(Integer::compareTo).get());
                put("pow", (ints) -> new Double(Math.pow(ints.get(0), ints.get(1))).intValue());
            }} ;

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

    @Override
    public Integer visit(Identifier id) {
        return null;
    }

    @Override
    public Integer visit(FunCall funCall) {
        String fnName = ((Identifier) funCall.name).name;

        List<Integer> args = new ArrayList<>();
        for (Node arg : funCall.argument) {
            args.add(arg.accept(this));
        }

        return FUNCTIONS.get(fnName).apply(args);
    }


}
