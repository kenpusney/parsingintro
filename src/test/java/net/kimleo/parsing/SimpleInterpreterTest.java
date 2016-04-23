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
    public void test_fun_call() throws Exception {
        int value = interpret("max(1, 2, 4, 10, 6, 8)");

        assertEquals(value, 10);

        value = interpret("pow(2, 1 + (2 * max(1, 2)))");
        assertEquals(value, 32);
    }
}