package net.kimleo.parsing;

import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.*;

import static net.kimleo.parsing.TestHelper.parse;
import static org.junit.Assert.assertEquals;
import static org.objectweb.asm.Opcodes.*;

public class JavaClassCompilerTest {

    @Test
    public void testCompileResult() throws Exception {
        Evaluable eval = createClass("TestCompileResult", "1 + 2 + 3 + 4");


        assertEquals(eval.eval(), 10);

        eval = createClass("TestCompileResult2", "1 * 2 * 3 * 4");

        assertEquals(eval.eval(), 24);


        eval = createClass("TestCompileResult3", "1 * 2 + 3 * 4");

        assertEquals(eval.eval(), 14);

        eval = createClass("TestCompileResult4", "max(1, 2)");

        assertEquals(eval.eval(), 2);
    }

    private Evaluable createClass(String className, String source) throws IllegalAccessException, InstantiationException, IOException {
        JavaClassCompiler compiler = new JavaClassCompiler(className);
        parse(source).accept(compiler);

        byte[] code = compiler.generate();

        Class aClass = new TestClassLoader().defineClass(className, code);

        return (Evaluable) aClass.newInstance();
    }

    private class TestClassLoader extends ClassLoader {
        Class defineClass(String name, byte[] value) {
            return defineClass(name, value, 0, value.length);
        }
    }

}