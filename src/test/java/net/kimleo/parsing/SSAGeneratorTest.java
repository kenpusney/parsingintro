package net.kimleo.parsing;

import org.junit.Test;

import static net.kimleo.parsing.TestHelper.generate;
import static org.junit.Assert.*;

public class SSAGeneratorTest {
    @Test
    public void generate_expressions() throws Exception {
        generate("(1 + 2) * (3 + 4 * (5 + 6))").forEach(System.out::println);
    }

    @Test
    public void generate_long_expression() throws Exception {
        generate("1 + 2 + 3 + 4 + 5 * 6 * 7 + 8 * 9").forEach(System.out::println);
    }
}