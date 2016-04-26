package net.kimleo.parsing;

import net.kimleo.parsing.ast.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class JavaClassCompiler implements Visitor<Object> {

    private final MethodVisitor method;
    private final ClassWriter cw;


    JavaClassCompiler(String className) {
        cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cw.visit(V1_7, ACC_PUBLIC, className, null, "java/lang/Object", new String[]{ "net/kimleo/parsing/Evaluable" });
        {
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL,
                    "java/lang/Object",
                    "<init>",
                    "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        {
            method = cw.visitMethod(ACC_PUBLIC, "eval", "()I", null, null);
        }
    }

    @Override
    public Object visit(Node node) {
        return node.accept(this);
    }

    @Override
    public Object visit(NumberLiteral num) {
        method.visitLdcInsn(num.value);
        return null;
    }

    @Override
    public Object visit(Expression expr) {
        expr.left.accept(this);
        expr.right.accept(this);
        switch (expr.operator) {
            case '+':
                method.visitInsn(IADD);
                break;
            case '-':
                method.visitInsn(ISUB);
                break;
            case '*':
                method.visitInsn(IMUL);
                break;
            case '/':
                method.visitInsn(IDIV);
                break;
            default:
                throw new UnsupportedOperationException("Unknown operator " + expr.operator);
        }
        return null;
    }

    @Override
    public Object visit(Identifier id) {
        method.visitMethodInsn(INVOKESTATIC, "java/lang/Math", id.name, "(II)I");
        return null;
    }

    @Override
    public Object visit(FunCall funCall) {
        if (funCall.argument.size() != 2)
            throw new UnsupportedOperationException("Unexpected FunCall");
        for (Node node : funCall.argument) {
            node.accept(this);
        }
        funCall.name.accept(this);
        return null;
    }

    public byte[] generate() {
        method.visitInsn(IRETURN);
        method.visitMaxs(0, 0);
        method.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }
}
