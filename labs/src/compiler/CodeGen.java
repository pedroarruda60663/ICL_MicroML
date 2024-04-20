package compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


import ast.*;
import ast.bools.*;
import ast.ints.*;
import symbols.Env;
import target.*;
import types.TypingException;


public class CodeGen implements ast.Exp.Visitor<Void, Env<Void>> {
	
	BasicBlock block = new BasicBlock();

	@Override
	public Void visit(ASTInt i, Env<Void> env) {
		block.addInstruction(new SIPushInt(i.value) );
		return null;
	}

	@Override
	public Void visit(ASTAdd e, Env<Void> env) throws TypingException {
	    e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IAdd());
	    return null;
	}

	@Override
	public Void visit(ASTMult e, Env<Void> env) throws TypingException {
	    e.arg1.accept(this, env);
	    e.arg2.accept(this, env);
	    block.addInstruction(new IMul());
		return null;
	}


	public Void visit(ASTDiv e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IDiv());
		return null;
	}

	@Override
	public Void visit(ASTSub e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new ISub());
		return null;
	}

	@Override
	public Void visit(ASTAnd e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IAdd());
		return null;
	}

	@Override
	public Void visit(ASTOr e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IOr());
		return null;
	}

	@Override
	public Void visit(ASTNot e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		block.addInstruction(new INot());
		return null;
	}

	@Override
	public Void visit(ASTLess e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new ILess());
		return null;
	}

	@Override
	public Void visit(ASTGreater e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IGreater());
		return null;
	}

	@Override
	public Void visit(ASTEquals e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IEquals());
		return null;
	}

	@Override
	public Void visit(ASTNotEquals e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new INotEquals());
		return null;
	}

	@Override
	public Void visit(ASTLessEq e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new ILessEq());
		return null;
	}

	@Override
	public Void visit(ASTGreaterEq e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IGreaterEq());
		return null;
	}

	@Override
	public Void visit(ASTIf e, Env<Void> env) throws TypingException {
		e.cond.accept(this, env);
		e.ifBody.accept(this, env);
		e.elseBody.accept(this, env);
		block.addInstruction(new IIf());
		return null;
	}

	@Override
	public Void visit(ASTBool e, Env<Void> env) {
		block.addInstruction(new SIPushBool(e.value) );
		return null;
	}

	@Override
	public Void visit(ASTLet e, Env<Void> env) throws TypingException {
		return null;
	}

	@Override
	public Void visit(ASTId e, Env<Void> env) throws TypingException {
		return null;
	}

	@Override
	public Void visit(ASTWhile e, Env<Void> env) throws TypingException {
		return null;
	}

	@Override
	public Void visit(ASTAssign e, Env<Void> env) throws TypingException {
		return null;
	}

	@Override
	public Void visit(ASTNew e, Env<Void> env) throws TypingException {
		return null;
	}

	@Override
	public Void visit(ASTDeref e, Env<Void> env) throws TypingException {
		return null;
	}

	@Override
	public Void visit(ASTUnit e, Env<Void> env) throws TypingException {
		return null;
	}


	public static BasicBlock codeGen(Exp e) throws TypingException {
		CodeGen cg = new CodeGen();
		Env<Void> globalEnv = new Env<>();
		e.accept(cg, globalEnv);
		return cg.block;
	}
	
	
	private static StringBuilder genPreAndPost(BasicBlock block) {
		String preamble = """
					  .class public Demo
					  .super java/lang/Object 
					  .method public <init>()V
					     aload_0
					     invokenonvirtual java/lang/Object/<init>()V
					     return
					  .end method
					  .method public static main([Ljava/lang/String;)V
					   .limit locals 10
					   .limit stack 256
					   ; setup local variables:
					   ;    1 - the PrintStream object held in java.lang.out
					  getstatic java/lang/System/out Ljava/io/PrintStream;					          
				   """;
		String footer = 
				"""
				invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
				invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
				return
				.end method
				""";
		StringBuilder sb = new StringBuilder(preamble);
		block.build(sb);
		sb.append(footer);
		return sb;
			
	}
	
	public static void writeToFile(Exp e, String filename) throws FileNotFoundException, TypingException {
	    StringBuilder sb = genPreAndPost(codeGen(e));
	    PrintStream out = new PrintStream(new FileOutputStream(filename));
	    out.print(sb.toString());
	    out.close();
		
	}

}
