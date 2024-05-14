package ast.ints;


import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTMult implements Exp {
	public Exp arg1;
	public Exp arg2;
	public Type type;
	
	public ASTMult(Exp arg1, Exp arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
		return v.visit(this, env);
	}
}
