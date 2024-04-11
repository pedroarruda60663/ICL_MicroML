package interpreter;

import ast.*;
import symbols.Env;
import types.TypingException;
import values.BoolValue;
import values.IntValue;
import values.Value;

public class Interpreter implements ast.Exp.Visitor<Value, Env<Value>> {

	@Override
	public Value visit(ASTInt i, Env<Value> env) {
		return new IntValue(i.value);
	}

	@Override
	public Value visit(ASTAdd e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new IntValue(n1.getValue() + n2.getValue());
	}

	@Override
	public Value visit(ASTMult e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new IntValue(n1.getValue() * n2.getValue());
	}
	@Override
	public Value visit(ASTDiv e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new IntValue(n1.getValue() / n2.getValue());
	}

	@Override
	public Value visit(ASTSub e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new IntValue(n1.getValue() - n2.getValue());
	}

	@Override
	public Value visit(ASTAnd e, Env<Value> env) throws TypingException {
		BoolValue n1 = e.arg1.accept(this, env).asBoolValue();
		BoolValue n2 = e.arg2.accept(this, env).asBoolValue();
		return new BoolValue(n1.getValue() && n2.getValue());
	}

	@Override
	public Value visit(ASTOr e, Env<Value> env) throws TypingException {
		BoolValue n1 = e.arg1.accept(this, env).asBoolValue();
		BoolValue n2 = e.arg2.accept(this, env).asBoolValue();
		return new BoolValue(n1.getValue() || n2.getValue());
	}

	@Override
	public Value visit(ASTNot e, Env<Value> env) throws TypingException {
		BoolValue n1 = e.arg1.accept(this, env).asBoolValue();
		return new BoolValue(!n1.getValue());
	}

	@Override
	public Value visit(ASTLess e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new BoolValue(n1.getValue() < n2.getValue());
	}

	@Override
	public Value visit(ASTGreater e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new BoolValue(n1.getValue() > n2.getValue());
	}

	@Override
	public Value visit(ASTEquals e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new BoolValue(((IntValue) v1).getValue() == ((IntValue) v2).getValue());
		} else {
			return new BoolValue(((BoolValue) v1).getValue() == ((BoolValue) v2).getValue());
		}
	}

	@Override
	public Value visit(ASTNotEquals e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new BoolValue(((IntValue) v1).getValue() != ((IntValue) v2).getValue());
		} else {
			return new BoolValue(((BoolValue) v1).getValue() != ((BoolValue) v2).getValue());
		}
	}

	@Override
	public Value visit(ASTBool e, Env<Value> env) throws TypingException {
		return new BoolValue(e.value);
	}

	@Override
	public Value visit(ASTLet e, Env<Value> env) throws TypingException {
		Env<Value> localEnv = env.beginScope();
		for (ASTVarDecl decl : e.varDecls) {
			Value val = decl.exp.accept(this, localEnv);
			localEnv.bind(decl.id, val);
		}
		Value result = e.body.accept(this, localEnv);
		// endScope() aqui?
		return result;
	}

	@Override
	public Value visit(ASTId e, Env<Value> env) throws TypingException {
		return env.find(e.id);
	}

	@Override
	public Value visit(ASTLessEq e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new BoolValue(n1.getValue() <= n2.getValue());
	}

	@Override
	public Value visit(ASTGreaterEq e, Env<Value> env) throws TypingException {
		IntValue n1 = e.arg1.accept(this, env).asIntValue();
		IntValue n2 = e.arg2.accept(this, env).asIntValue();
		return new BoolValue(n1.getValue() >= n2.getValue());
	}

	public static Value interpret(Exp e) throws TypingException {
		Interpreter i = new Interpreter();
		Env<Value> globalEnv = new Env<>();
		return e.accept(i, globalEnv);
	}
	
}
