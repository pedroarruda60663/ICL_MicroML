package ast;


import symbols.Env;
import types.Type;
import types.TypingException;
import values.Value;

public interface Exp {
	public interface Visitor<T, E> {
		public T visit(ASTInt i, E env) throws TypingException;
		public T visit(ASTAdd e, E env) throws TypingException;
		public T visit(ASTMult e, E env) throws TypingException;
		public T visit(ASTDiv e, E env) throws TypingException;
		public T visit(ASTSub e, E env) throws TypingException;
		public T visit(ASTAnd e, E env) throws TypingException;
		public T visit(ASTOr e, E env) throws TypingException;
		public T visit(ASTNot e, E env) throws TypingException;
		public T visit(ASTLess e, E env) throws TypingException;
		public T visit(ASTGreater e, E env) throws TypingException;
		public T visit(ASTEquals e, E env) throws TypingException;
		public T visit(ASTNotEquals e, E env) throws TypingException;
		public T visit(ASTBool e, E env) throws TypingException;
		public T visit(ASTLet e, E env) throws TypingException;
		public T visit(ASTId e, E env) throws TypingException;
		public T visit(ASTLessEq e, E env) throws TypingException;
		public T visit(ASTGreaterEq e, E env) throws TypingException;
		public T visit(ASTIf e, E env) throws TypingException;
    }
	
    //public Type typeCheck() throws TypingException;
	
	public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException;
	
	
}

