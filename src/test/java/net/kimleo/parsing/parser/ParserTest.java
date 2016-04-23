package net.kimleo.parsing.parser;

import static net.kimleo.parsing.TestHelper.parse;

import net.kimleo.parsing.ast.Expression;
import net.kimleo.parsing.ast.Node;
import net.kimleo.parsing.ast.NumberLiteral;
import org.junit.Test;

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

    private Expression expr(char operator, Node right, Node left) {
        return new Expression(operator,  left, right);
    }

    private NumberLiteral num(int value) {
        return new NumberLiteral(value);
    }
}