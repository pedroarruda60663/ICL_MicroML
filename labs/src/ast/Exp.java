package ast;


import ast.arrays.ASTArrayAccess;
import ast.arrays.ASTArrayAssign;
import ast.arrays.ASTNewArray;
import ast.bools.*;
import ast.declarations.ASTId;
import ast.declarations.ASTLet;
import ast.functions.ASTFunCall;
import ast.functions.ASTFunDef;
import ast.ints.*;
import ast.references.ASTAssign;
import ast.references.ASTDeref;
import ast.references.ASTNew;
import types.Type;
import types.TypingException;

import java.io.FileNotFoundException;

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
		public T visit(ASTWhile e, E env) throws TypingException;
		public T visit(ASTAssign e, E env) throws TypingException;
		public T visit(ASTNew e, E env) throws TypingException;
		public T visit(ASTDeref e, E env) throws TypingException;
		public T visit(ASTUnit e, E env) throws TypingException;
		public T visit(ASTPrint e, E env) throws TypingException;
		public T visit(ASTSeq e, E env) throws TypingException;
        public T visit(ASTFunDef e, E env) throws TypingException;
		public T visit(ASTFunCall e, E env) throws TypingException;
		public T visit(ASTNewArray e, E env) throws TypingException;
		public T visit(ASTArrayAssign e, E env) throws TypingException;
		public T visit(ASTArrayAccess e, E env) throws TypingException;
	}
	public Type getType();
	public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException;
	
	
}

