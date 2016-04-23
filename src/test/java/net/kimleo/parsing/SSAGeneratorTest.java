package net.kimleo.parsing;

import org.junit.Test;

import static net.kimleo.parsing.TestHelper.generate;
import static org.junit.Assert.*;

public class SSAGeneratorTest {
    @Test
    public void generate_expressions() throws Exception {
        SSAGenerator generator = generate("(1 + 2) * (3 + 4 * (5 + 6))");

        generator.SSAStatement.forEach(System.out::println);
    }
}