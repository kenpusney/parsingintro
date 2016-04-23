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

        assertEquals(node, new NumberLiteral(1));
    }
}