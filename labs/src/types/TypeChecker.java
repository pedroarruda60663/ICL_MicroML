package types;

import ast.*;
import ast.arrays.ASTArrayAccess;
import ast.arrays.ASTArrayAssign;
import ast.arrays.ASTNewArray;
import ast.bools.*;
import ast.declarations.ASTId;
import ast.declarations.ASTLet;
import ast.declarations.ASTVarDecl;
import ast.functions.ASTFunCall;
import ast.functions.ASTFunDef;
import ast.nums.*;
import ast.references.ASTAssign;
import ast.references.ASTDeref;
import ast.references.ASTNew;
import symbols.Env;

import java.util.ArrayList;
import java.util.List;


public class TypeChecker implements ast.Exp.Visitor<Type, Env<Type>> {

    @Override
    public Type visit(ASTInt i, Env<Type> env) throws TypingException{
        i.type = IntType.getInstance();
        return IntType.getInstance();
    }

    @Override
    public Type visit(ASTAdd e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);
        if (type1.isDoubleType() || type2.isDoubleType()){
            e.type = DoubleType.getInstance();
            return DoubleType.getInstance();
        }else {
            e.type = IntType.getInstance();
            return IntType.getInstance();
        }
    }

    @Override
    public Type visit(ASTMult e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);
        if (type1.isDoubleType() || type2.isDoubleType()){
            e.type = DoubleType.getInstance();
            return DoubleType.getInstance();
        }else {
            e.type = IntType.getInstance();
            return IntType.getInstance();
        }
    }

    @Override
    public Type visit(ASTDiv e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);
        if (type1.isDoubleType() || type2.isDoubleType()){
            e.type = DoubleType.getInstance();
            return DoubleType.getInstance();
        }else {
            e.type = IntType.getInstance();
            return IntType.getInstance();
        }
    }

    @Override
    public Type visit(ASTSub e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);
        if (type1.isDoubleType() || type2.isDoubleType()){
            e.type = DoubleType.getInstance();
            return DoubleType.getInstance();
        }else {
            e.type = IntType.getInstance();
            return IntType.getInstance();
        }
    }

    @Override
    public Type visit(ASTAnd e, Env<Type> env) throws TypingException {
        ensureBoolType(e.arg1.accept(this, env));
        ensureBoolType(e.arg2.accept(this, env));
        e.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTOr e, Env<Type> env) throws TypingException {
        ensureBoolType(e.arg1.accept(this, env));
        ensureBoolType(e.arg2.accept(this, env));
        e.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTNot e, Env<Type> env) throws TypingException {
        ensureBoolType(e.arg1.accept(this, env));
        e.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTLess e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);

        e.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTGreater e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);

        e.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTEquals e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);

        if ((type1.isIntType() || type1.isDoubleType()) && (type2.isIntType() || type2.isDoubleType() )) {
            e.type = BoolType.getInstance();
            return BoolType.getInstance();
        } else if (type1.isBoolType() && type2.isBoolType()) {
            e.type = BoolType.getInstance();
            return BoolType.getInstance();
        } else {
            throw new TypingException("Operands of '==' must be of the same type.");
        }
    }

    @Override
    public Type visit(ASTNotEquals e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);

        if ((type1.isIntType() || type1.isDoubleType()) && (type2.isIntType() || type2.isDoubleType() )) {
            e.type = BoolType.getInstance();
            return BoolType.getInstance();
        } else if (type1.isBoolType() && type2.isBoolType()) {
            e.type = BoolType.getInstance();
            return BoolType.getInstance();
        } else {
            throw new TypingException("Operands of '!=' must be of the same type.");
        }
    }

    @Override
    public Type visit(ASTLessEq e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);

        e.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTGreaterEq e, Env<Type> env) throws TypingException {
        Type type1 = e.arg1.accept(this, env);
        Type type2 = e.arg2.accept(this, env);
        ensureNumericalType(type1);
        ensureNumericalType(type2);

        e.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTBool b, Env<Type> env) throws TypingException {
        b.type = BoolType.getInstance();
        return BoolType.getInstance();
    }

    @Override
    public Type visit(ASTLet e, Env<Type> env) throws TypingException {
        Env<Type> localEnv = env.beginScope();
        for (ASTVarDecl decl : e.varDecls) {
            Type type = decl.exp.accept(this, localEnv);
            decl.type = type;
            localEnv.bind(decl.id, type);
        }
        Type result = e.body.accept(this, localEnv);
        e.type = result;
        return result;
    }

    @Override
    public Type visit(ASTId e, Env<Type> env) throws TypingException {
        Type type = env.find(e.id);
        if (type == null) {
            throw new TypingException("Undeclared identifier: " + e.id);
        }
        e.type = type;
        return type;
    }

    @Override
    public Type visit(ASTIf e, Env<Type> env) throws TypingException {
        ensureBoolType(e.cond.accept(this, env));
        Type ifType = e.ifBody.accept(this, env);
        if(e.elseBody == null) {
            e.type = UnitType.getInstance();
            return UnitType.getInstance();
        }
        else {
            Type elseType = e.elseBody.accept(this, env);
            if (!ifType.equals(elseType)) {
                throw new TypingException("If-else branches must have the same type.");
            }
            e.type = elseType;
            return elseType;
        }
    }

    @Override
    public Type visit(ASTWhile e, Env<Type> env) throws TypingException {
        Type condType = e.condition.accept(this, env);
        if (!condType.isBoolType()) {
            throw new TypingException("While loop condition must be boolean.");
        }

        e.body.accept(this, env);
        e.type = UnitType.getInstance();
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
        e.type = newValueType;
        return newValueType;
    }

    @Override
    public Type visit(ASTNew e, Env<Type> env) throws TypingException {
        RefType refType = new RefType(e.arg.accept(this, env));
        e.type = refType;
        return refType;
    }

    @Override
    public Type visit(ASTDeref e, Env<Type> env) throws TypingException {
        Type exprType = e.arg.accept(this, env);
        if (!exprType.isRefType()) {
            throw new TypingException("Dereferencing a non-reference type.");
        }
        e.type = ((RefType) exprType).getInner();
        return ((RefType) exprType).getInner();

    }

    @Override
    public Type visit(ASTUnit e, Env<Type> env) throws TypingException {
        e.type = UnitType.getInstance();
        return UnitType.getInstance();
    }

    @Override
    public Type visit(ASTPrint e, Env<Type> env) throws TypingException {
        Type printType = e.print.accept(this, env);
        if (printType.isRefType() || printType.isFunType()) {
            throw new TypingException("Can not print this type of expression.");
        }
        e.type = printType;
        return UnitType.getInstance();
    }

    @Override
    public Type visit(ASTSeq e, Env<Type> env) throws TypingException {
        e.first.accept(this, env);
        Type type = e.second.accept(this, env);
        e.type = type;
        return type;
    }

    @Override
    public Type visit(ASTFunDef e, Env<Type> env) throws TypingException {

        Env<Type> newEnv = env.beginScope();
        List<Type> paramTypes = new ArrayList<>();

        for (int i = 0; i < e.params.size(); i++) {
            Type paramType = e.params.get(i).second;
            paramTypes.add(paramType);
            newEnv.bind(e.params.get(i).first, paramType);
        }

        Type body = e.body.accept(this, newEnv);
        newEnv.endScope();
        FunType funType = new FunType(paramTypes, body);
        e.type = funType;
        return funType;
    }

    @Override
    public Type visit(ASTFunCall e, Env<Type> env) throws TypingException {
        Type funcType = e.id.accept(this, env);

        if (!(funcType instanceof FunType funType)) {
            throw new TypingException("Expected function type, but found: " + funcType);
        }

        List<Type> paramTypes = funType.arguments;
        List<Exp> args = e.params;

        if (paramTypes.size() != args.size()) {
            throw new TypingException("Function called with wrong number of arguments");
        }

        for (int i = 0; i < args.size(); i++) {
            Type argType = args.get(i).accept(this, env);
            Type paramType = paramTypes.get(i);

            if (!argType.equals(paramType)) {
                throw new TypingException("Argument type mismatch: expected " + paramType + " but found " + argType);
            }
        }
        e.type = funType.resultType;
        return funType.resultType;
    }

    @Override
    public Type visit(ASTNewArray e, Env<Type> env) throws TypingException {
        Type sizeType = e.size.accept(this, env);
        ensureIntType(sizeType);
        if(e.elementType.equals("int"))
            e.type = new ArrayType(new IntType());
        else if(e.elementType.equals("bool"))
            e.type = new ArrayType(new BoolType());
        else
            throw new TypingException("Invalid array type: " + e.elementType);
        return e.type;
    }

    @Override
    public Type visit(ASTArrayAssign e, Env<Type> env) throws TypingException {
        Type arrayType = e.array.accept(this, env);
        if (!(arrayType instanceof ArrayType array)) {
            throw new TypingException("Array assignment to a non-array type");
        }

        Type indexType = e.index.accept(this, env);
        if (!(indexType instanceof IntType)) {
            throw new TypingException("Array index must be an integer");
        }

        Type valueType = e.newValue.accept(this, env);
        Type elementType = array.getElementType();
        if (!valueType.toString().equals(elementType.toString())) {
            throw new TypingException("Array assignment type mismatch");
        }
        e.type = valueType;
        return valueType;
    }

    @Override
    public Type visit(ASTArrayAccess e, Env<Type> env) throws TypingException {
        Type arrayType = e.array.accept(this, env);
        if (!(arrayType instanceof ArrayType array)) {
            throw new TypingException("Accessing a non-array type as an array");
        }

        Type indexType = e.index.accept(this, env);
        if (!(indexType instanceof IntType)) {
            throw new TypingException("Array index must be an integer");
        }

        e.type = array.getElementType();
        return array.getElementType();
    }

    @Override
    public Type visit(ASTDouble e, Env<Type> env) throws TypingException {
        e.type = DoubleType.getInstance();
        return DoubleType.getInstance();
    }

    public static Type typeCheck(Exp e) throws TypingException {
        TypeChecker i = new TypeChecker();
        Env<Type> globalEnv = new Env<>();
        return e.accept(i, globalEnv);
    }

    private void ensureIntType(Type t) throws TypingException {
        if (!t.isIntType()) {
            throw new TypingException("Expected integer type, but found: " + t);
        }
    }

    private void ensureBoolType(Type t) throws TypingException {
        if (!t.isBoolType()) {
            throw new TypingException("Expected boolean type, but found: " + t);
        }
    }

    private void ensureNumericalType(Type t) throws TypingException {
        if (!t.isIntType() && !t.isDoubleType()) {
            throw new TypingException("Expected numerical (int or double) type, but found: " + t);
        }
    }
}

