package types;

import ast.*;
import symbols.Env;


public class TypeChecker implements ast.Exp.Visitor<Type, Env<Type>> {

    @Override
    public Type visit(ASTInt i, Env<Type> env) throws TypingException{
        return IntType.getInstance();
    }

    @Override
    public Type visit(ASTAdd e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return IntType.getInstance();
    }

    @Override
    public Type visit(ASTMult e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return IntType.getInstance();
    }

    @Override
    public Type visit(ASTDiv e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return IntType.getInstance();
    }

    @Override
    public Type visit(ASTSub e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return IntType.getInstance();
    }

    @Override
    public Type visit(ASTAnd e, Env<Type> env) throws TypingException {
        ensureBoolType(e.arg1.accept(this, env));
        ensureBoolType(e.arg2.accept(this, env));
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTOr e, Env<Type> env) throws TypingException {
        ensureBoolType(e.arg1.accept(this, env));
        ensureBoolType(e.arg2.accept(this, env));
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTNot e, Env<Type> env) throws TypingException {
        ensureBoolType(e.arg1.accept(this, env));
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTLess e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTGreater e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTEquals e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);

        if (type1.isIntType() && type2.isIntType()) {
            return BoolType.getInstance();
        } else if (type1.isBoolType() && type2.isBoolType()) {
            return BoolType.getInstance();
        } else {
            throw new TypingException("Operands of '==' must be of the same type.");
        }
    }

    @Override
    public Type visit(ASTNotEquals e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);

        if (type1.isIntType() && type2.isIntType()) {
            return BoolType.getInstance();
        } else if (type1.isBoolType() && type2.isBoolType()) {
            return BoolType.getInstance();
        } else {
            throw new TypingException("Operands of '!=' must be of the same type.");
        }
    }

    @Override
    public Type visit(ASTBool b, Env<Type> env) throws TypingException {
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTLet e, Env<Type> env) throws TypingException {
        Env<Type> localEnv = env.beginScope();
        for (ASTVarDecl decl : e.varDecls) {
            Type type = decl.exp.accept(this, localEnv);
            localEnv.bind(decl.id, type);
        }
        Type result = e.body.accept(this, localEnv);
        // endScope() aqui?
        return result;
    }

    @Override
    public Type visit(ASTId e, Env<Type> env) throws TypingException {
        Type type = env.find(e.id);
        if (type == null) {
            throw new TypingException("Undeclared identifier: " + e.id);
        }
        return type;
    }

    public static Type typeCheck(Exp e) throws TypingException {
        TypeChecker i = new TypeChecker();
        Env<Type> globalEnv = new Env<>();
        return e.accept(i, globalEnv);
    }

    private void ensureIntType(Type t) throws TypingException {
        if (!t.isIntType()) {
            throw new TypingException("Expected integer type");
        }
    }

    private void ensureBoolType(Type t) throws TypingException {
        if (!t.isBoolType()) {
            throw new TypingException("Expected boolean type");
        }
    }

}

