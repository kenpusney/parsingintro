package net.kimleo.parsing.parser;

import static net.kimleo.parsing.TestHelper.parse;

import net.kimleo.parsing.ast.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void parse_single_number() throws Exception {
        Node node = parse("1");

        assertEquals(node, num(1));
    }

    @Test
    public void parse_expression() throws Exception {
        Node node = parse("1 + 1");

        assertEquals(node, expr('+', num(1), num(1)));
    }

    @Test
    public void parse_complex_expression() throws Exception {
        Node node = parse("1 * 2 + 3");

        assertEquals(node, expr('+', expr('*', num(1), num(2)), num(3)));
    }

    @Test
    public void parse_with_parens() throws Exception {
        Node node = parse("1 * (2 + 3)");

        assertEquals(node, expr('*', num(1), expr('+', num(2), num(3))));
    }

    @Test
    public void parse_fun_call() throws Exception {
        Node node = parse("max(1, 2)");

        assertEquals(node, funcall("max", Arrays.asList(num(1), num(2))));
    }

    private FunCall funcall(String name, List<Node> arguments) {
        return new FunCall(new Identifier(name), arguments);
    }

    private Expression expr(char operator, Node left, Node right) {
        return new Expression(operator, left, right);
    }

    private NumberLiteral num(int value) {
        return new NumberLiteral(value);
    }
}