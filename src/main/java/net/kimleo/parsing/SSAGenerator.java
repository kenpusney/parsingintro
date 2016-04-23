package net.kimleo.parsing;

import net.kimleo.parsing.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SSAGenerator implements Visitor<String> {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public final List<String> SSAStatement = new ArrayList<>();
    int count = 0;

    @Override
    public String visit(Node node) {
        return node.accept(this);
    }

    @Override
    public String visit(NumberLiteral num) {
        String name = generateSSAName();
        SSAStatement.add(String.format("%s = %d", name, num.value));
        return name;
    }

    @Override
    public String visit(Expression expr) {
        String left = expr.left.accept(this);
        String right = expr.right.accept(this);
        String name = generateSSAName();
        SSAStatement.add(String.format("%s = %s %s %s", name, left, expr.operator, right));
        return name;
    }

    @Override
    public String visit(Identifier id) {
        return id.name;
    }

    @Override
    public String visit(FunCall funCall) {
        String fnName = funCall.name.accept(this);
        String argument = funCall.argument.stream().map(n -> n.accept(this)).collect(Collectors.joining(", "));
        String name = generateSSAName();

        SSAStatement.add(String.format("%s = %s(%s)", name, fnName, argument));
        return name;
    }

    private String generateSSAName() {
        int current = count;
        count++;
        StringBuilder sb = new StringBuilder();
        while (current > 26) {
            sb.append(ALPHABET.charAt(current % 26));
            current /= 26;
        }
        sb.append(ALPHABET.charAt(current));
        return sb.reverse().toString();
    }
}
