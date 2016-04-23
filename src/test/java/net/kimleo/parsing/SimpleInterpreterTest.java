package net.kimleo.parsing;

import net.kimleo.parsing.ast.Expression;
import net.kimleo.parsing.ast.Node;
import net.kimleo.parsing.ast.NumberLiteral;
import org.junit.Test;

import static net.kimleo.parsing.TestHelper.interpret;
import static net.kimleo.parsing.TestHelper.parse;
import static org.junit.Assert.*;

public class SimpleInterpreterTest {
    @Test
    public void test_simple_expression() throws Exception {
        int value = interpret("1 + 2 * 3");
        assertEquals(value, 7);
    }
    @Test
    public void parse_expression() throws Exception {
        Node node = parse("1 + 1");

        assertEquals(node, new Expression('+', new NumberLiteral(1), new NumberLiteral(1)));
    }
}