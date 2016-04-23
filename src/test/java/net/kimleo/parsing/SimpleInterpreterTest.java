package net.kimleo.parsing;

import org.junit.Test;

import static net.kimleo.parsing.TestHelper.interpret;
import static org.junit.Assert.assertEquals;

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