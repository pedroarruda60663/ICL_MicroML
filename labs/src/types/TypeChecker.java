package types;

import ast.*;
import ast.bools.*;
import ast.ints.*;
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
    public Type visit(ASTLessEq e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTGreaterEq e, Env<Type> env) throws TypingException {
        ensureIntType(e.arg1.accept(this, env));
        ensureIntType(e.arg2.accept(this, env));
        return BoolType.getInstance();
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

    @Override
    public Type visit(ASTIf e, Env<Type> env) throws TypingException {
        ensureBoolType(e.cond.accept(this, env));
        Type ifType = e.ifBody.accept(this, env);
        if(e.elseBody == null) {
            return ifType;
        }
        else {
            Type elseType = e.elseBody.accept(this, env);
            if (!ifType.equals(elseType)) {
                throw new TypingException("If-else branches must have the same type.");
            }
            return elseType;
        }
    }

    @Override
    public Type visit(ASTWhile e, Env<Type> env) throws TypingException {
        Type condType = e.condition.accept(this, env);
        if (!condType.isBoolType()) {
            throw new TypingException("While loop condition must be boolean.");
        }

        //if body type ever needs to be checked
        e.body.accept(this, env);

        return UnitType.getInstance();
    }

    @Override
    public Type visit(ASTAssign e, Env<Type> env) throws TypingException {
        Type referenceType = e.reference.accept(this, env);
        Type newValueType = e.newValue.accept(this, env);

        if (referenceType.isRefType()) {
            Type innerType = ((RefType) referenceType).getInner();
            if (!newValueType.equals(innerType)) {
                throw new TypingException("Type mismatch in assignment: expected " + innerType + ", found " + newValueType);
            }
        } else {
            throw new TypingException("Left-hand side of assignment must be a reference type.");
        }

        return newValueType;
    }

    @Override
    public Type visit(ASTNew e, Env<Type> env) throws TypingException {
        Type innerType = e.arg.accept(this, env);
        return new RefType(innerType);
    }

    @Override
    public Type visit(ASTDeref e, Env<Type> env) throws TypingException {
        Type exprType = e.arg.accept(this, env);
        if (!exprType.isRefType()) {
            throw new TypingException("Dereferencing a non-reference type.");
        }

        return ((RefType) exprType).getInner();

    }

    @Override
    public Type visit(ASTUnit e, Env<Type> env) throws TypingException {
        return UnitType.getInstance();
    }

    @Override
    public Type visit(ASTPrint e, Env<Type> env) throws TypingException {
        Type referenceType = e.print.accept(this, env);
        return new RefType(referenceType);
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

