package interpreter;

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
import types.TypingException;
import values.*;

import java.util.ArrayList;
import java.util.List;

public class Interpreter implements ast.Exp.Visitor<Value, Env<Value>> {

	@Override
	public Value visit(ASTInt i, Env<Value> env) {
		return new IntValue(i.value);
	}

	@Override
	public Value visit(ASTAdd e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new IntValue(v1.asIntValue().getValue() + v2.asIntValue().getValue());
		} else {
			double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
			double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();
			return new DoubleValue(d1 + d2);
		}
	}

	@Override
	public Value visit(ASTMult e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new IntValue(v1.asIntValue().getValue() * v2.asIntValue().getValue());
		} else {
			double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
			double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();
			return new DoubleValue(d1 * d2);
		}
	}
	@Override
	public Value visit(ASTDiv e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new IntValue(v1.asIntValue().getValue() / v2.asIntValue().getValue());
		} else {
			double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
			double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();
			return new DoubleValue(d1 / d2);
		}
	}

	@Override
	public Value visit(ASTSub e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new IntValue(v1.asIntValue().getValue() - v2.asIntValue().getValue());
		} else {
			double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
			double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();
			return new DoubleValue(d1 - d2);
		}
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
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
		double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();

		return new BoolValue(d1 < d2);
	}

	@Override
	public Value visit(ASTGreater e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
		double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();

		return new BoolValue(d1 > d2);
	}

	@Override
	public Value visit(ASTEquals e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new BoolValue(((IntValue) v1).getValue() == ((IntValue) v2).getValue());
		} else if (v1 instanceof DoubleValue && v2 instanceof DoubleValue) {
			return new BoolValue(((DoubleValue) v1).getValue() == ((DoubleValue) v2).getValue());
		} else if (v1 instanceof BoolValue && v2 instanceof BoolValue) {
			return new BoolValue(((BoolValue) v1).getValue() == ((BoolValue) v2).getValue());
		} else if (v1 instanceof IntValue && v2 instanceof DoubleValue) {
			return new BoolValue(((IntValue) v1).getValue() == ((DoubleValue) v2).getValue());
		} else {
			return new BoolValue(((DoubleValue) v1).getValue() == ((IntValue) v2).getValue());
		}
	}

	@Override
	public Value visit(ASTNotEquals e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		if (v1 instanceof IntValue && v2 instanceof IntValue) {
			return new BoolValue(((IntValue) v1).getValue() != ((IntValue) v2).getValue());
		} else if (v1 instanceof DoubleValue && v2 instanceof DoubleValue) {
			return new BoolValue(((DoubleValue) v1).getValue() != ((DoubleValue) v2).getValue());
		} else if (v1 instanceof BoolValue && v2 instanceof BoolValue) {
			return new BoolValue(((BoolValue) v1).getValue() != ((BoolValue) v2).getValue());
		} else if (v1 instanceof IntValue && v2 instanceof DoubleValue) {
			return new BoolValue(((IntValue) v1).getValue() != ((DoubleValue) v2).getValue());
		} else {
			return new BoolValue(((DoubleValue) v1).getValue() != ((IntValue) v2).getValue());
		}
	}

	@Override
	public Value visit(ASTLessEq e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
		double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();

		return new BoolValue(d1 <= d2);
	}

	@Override
	public Value visit(ASTGreaterEq e, Env<Value> env) throws TypingException {
		Value v1 = e.arg1.accept(this, env);
		Value v2 = e.arg2.accept(this, env);

		double d1 = v1 instanceof IntValue ? v1.asIntValue().getValue() : v1.asDoubleValue().getValue();
		double d2 = v2 instanceof IntValue ? v2.asIntValue().getValue() : v2.asDoubleValue().getValue();

		return new BoolValue(d1 >= d2);
	}

	@Override
	public Value visit(ASTIf e, Env<Value> env) throws TypingException {
		BoolValue condition = e.cond.accept(this, env).asBoolValue();

		if(e.elseBody == null) {
			if(condition.getValue()){
				e.ifBody.accept(this, env);
			}
			return UnitValue.getInstance();
		}
		else {
			if(condition.getValue()){
				return e.ifBody.accept(this, env);
			}
			return e.elseBody.accept(this, env);
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

		return result;
	}

	@Override
	public Value visit(ASTId e, Env<Value> env) throws TypingException {
		return env.find(e.id);
	}

	@Override
	public Value visit(ASTWhile e, Env<Value> env) throws TypingException {

		while (e.condition.accept(this, env).asBoolValue().getValue()) {
			 e.body.accept(this, env);
		}
		return UnitValue.getInstance();
	}

	@Override
	public Value visit(ASTAssign e, Env<Value> env) throws TypingException {

		Value newValue = e.newValue.accept(this, env);
		RefValue refValue = e.reference.accept(this, env).asRefValue();
		refValue.setValue(newValue);
		return newValue;
	}

	@Override
	public Value visit(ASTNew e, Env<Value> env) throws TypingException {
		RefValue refValue = new RefValue(e.arg.accept(this, env));
		return refValue;
	}

	@Override
	public Value visit(ASTDeref e, Env<Value> env) throws TypingException {
		RefValue refValue = e.arg.accept(this, env).asRefValue();
		return refValue.getValue();
	}

	@Override
	public Value visit(ASTUnit e, Env<Value> env) throws TypingException {
		return UnitValue.getInstance();
	}

	@Override
	public Value visit(ASTPrint e, Env<Value> env) throws TypingException {
		Value value = e.print.accept(this, env);
		System.out.println(value);
		return UnitValue.getInstance();
	}

	@Override
	public Value visit(ASTSeq e, Env<Value> env) throws TypingException {
		e.first.accept(this, env);
		return e.second.accept(this, env);
	}

	@Override
	public Value visit(ASTFunDef e, Env<Value> env) throws TypingException {
		ClosureValue closureValue = new ClosureValue(e.params, e.body, env);
		return closureValue;
	}

	@Override
	public Value visit(ASTFunCall e, Env<Value> env) throws TypingException {

		ClosureValue function = e.id.accept(this, env).asClosureValue();

		List<Value> argValues = new ArrayList<>();
		for (Exp arg : e.params) {
			argValues.add(arg.accept(this, env));
		}

		Env<Value> newEnv = function.env.beginScope();
		for (int i = 0; i < function.params.size(); i++) {
			newEnv.bind(function.params.get(i).first, argValues.get(i));
		}

		Value result = function.body.accept(this, newEnv);
		newEnv.endScope();
		return result;
	}

	@Override
	public Value visit(ASTNewArray e, Env<Value> env) throws TypingException {
		int size = e.size.accept(this, env).asIntValue().getValue();
		Value[] values = new Value[size];
		if(e.elementType.equals("int")) {
			for (int i = 0; i < size; i++) {
				values[i] = new IntValue(0);
			}
		}
		else if (e.elementType.equals("bool")) {
			for (int i = 0; i < size; i++) {
				values[i] = new BoolValue(false);
			}
		}
		return new ArrayValues(values);
	}

	@Override
	public Value visit(ASTArrayAssign e, Env<Value> env) throws TypingException {
		ArrayValues array = e.array.accept(this, env).asArrayValue();
		int index = e.index.accept(this, env).asIntValue().getValue();
		Value newValue = e.newValue.accept(this, env);
		Value oldValue = array.getValueAt(index);

		if(oldValue instanceof IntValue && newValue instanceof IntValue) {
			array.setValueAt(index, newValue);
			return newValue;
		}
		else if(oldValue instanceof BoolValue && newValue instanceof BoolValue) {
			array.setValueAt(index, newValue);
			return newValue;
		}
		return newValue;
	}

	@Override
	public Value visit(ASTArrayAccess e, Env<Value> env) throws TypingException {
		ArrayValues array = e.array.accept(this, env).asArrayValue();
		int index = e.index.accept(this, env).asIntValue().getValue();
		return array.getValueAt(index);
	}

	@Override
	public Value visit(ASTDouble e, Env<Value> env) throws TypingException {
		return new DoubleValue(e.value);
	}

	public static Value interpret(Exp e) throws TypingException {
		Interpreter i = new Interpreter();
		Env<Value> globalEnv = new Env<>();
		return e.accept(i, globalEnv);
	}
	
}
