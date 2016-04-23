package net.kimleo.parsing.lexer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static net.kimleo.parsing.lexer.Lexer.number;
import static net.kimleo.parsing.lexer.Lexer.operator;
import static net.kimleo.parsing.lexer.TestHelper.lex;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LexerTest {
    @Test
    public void lex_single_matcher() throws Exception {
        assertEquals(lex("1"), Collections.singletonList(number(1)));
    }

    @Test
    public void lex_simple_expression() throws Exception {
        assertEquals(lex("1 + 1"), Arrays.asList(number(1), operator('+'), number(1)));
    }
}