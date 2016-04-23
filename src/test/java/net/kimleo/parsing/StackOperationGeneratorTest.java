package net.kimleo.parsing;

import org.junit.Test;

import static net.kimleo.parsing.TestHelper.stackGen;

public class StackOperationGeneratorTest {
    @Test
    public void generate_expressions() throws Exception {
        stackGen("pow(1 + 2 + max(3, 5, 4), 8)").forEach(System.out::println);
    }
}