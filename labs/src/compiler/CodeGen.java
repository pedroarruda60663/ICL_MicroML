package compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import ast.*;
import ast.bools.*;
import ast.declarations.ASTId;
import ast.declarations.ASTLet;
import ast.declarations.ASTVarDecl;
import ast.functions.ASTFunCall;
import ast.functions.ASTFunDef;
import ast.ints.*;
import ast.references.ASTAssign;
import ast.references.ASTDeref;
import ast.references.ASTNew;
import symbols.CompEnv;
import symbols.Env;
import instructions.*;
import symbols.Pair;
import types.*;
import values.ClosureValue;


public class CodeGen implements ast.Exp.Visitor<Void, Env<Void>> {

	BlockSeq block = new BlockSeq();
	private Set<Pair<String, String>> refTypes = new HashSet<>();
	private Set<Pair<String, List<Type>>> funTypes = new HashSet<>();

	@Override
	public Void visit(ASTInt i, Env<Void> env) {
		block.addInstruction(new SIPush(i.value) );
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
	public Void visit(ASTBool e, Env<Void> env) {
		int intBool = e.value ? 1 : 0;
		block.addInstruction(new SIPush(intBool) );
		return null;
	}

	@Override
	public Void visit(ASTAnd e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);
		block.addInstruction(new IAnd());
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
		block.addInstruction(new SIPush(1));
		block.addInstruction(new IXor());
		return null;
	}

	@Override
	public Void visit(ASTLess e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);

		Label trueLabel = new Label();
		Label endLabel = new Label();
		block.addInstruction(new ILess(trueLabel));

		// If comparison is false, push 0 (false) and jump to endLabel
		block.addInstruction(new SIPush(0));
		block.addInstruction(new Goto(endLabel));

		// Label location where comparison is true
		block.addLabel(trueLabel);
		block.addInstruction(new SIPush(1)); // Push 1 (true) on stack

		// End label location to resume normal execution
		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTGreater e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);

		Label trueLabel = new Label();
		Label endLabel = new Label();
		block.addInstruction(new IGreater(trueLabel));

		block.addInstruction(new SIPush(0));
		block.addInstruction(new Goto(endLabel));

		block.addLabel(trueLabel);
		block.addInstruction(new SIPush(1));

		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTEquals e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);

		Label trueLabel = new Label();
		Label endLabel = new Label();
		block.addInstruction(new IEquals(trueLabel));

		block.addInstruction(new SIPush(0));
		block.addInstruction(new Goto(endLabel));

		block.addLabel(trueLabel);
		block.addInstruction(new SIPush(1));

		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTNotEquals e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);

		Label trueLabel = new Label();
		Label endLabel = new Label();
		block.addInstruction(new INotEquals(trueLabel));

		block.addInstruction(new SIPush(0));
		block.addInstruction(new Goto(endLabel));

		block.addLabel(trueLabel);
		block.addInstruction(new SIPush(1));

		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTLessEq e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);

		Label trueLabel = new Label();
		Label endLabel = new Label();
		block.addInstruction(new ILessEq(trueLabel));

		block.addInstruction(new SIPush(0));
		block.addInstruction(new Goto(endLabel));

		block.addLabel(trueLabel);
		block.addInstruction(new SIPush(1));

		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTGreaterEq e, Env<Void> env) throws TypingException {
		e.arg1.accept(this, env);
		e.arg2.accept(this, env);

		Label trueLabel = new Label();
		Label endLabel = new Label();
		block.addInstruction(new IGreaterEq(trueLabel));

		block.addInstruction(new SIPush(0));
		block.addInstruction(new Goto(endLabel));

		block.addLabel(trueLabel);
		block.addInstruction(new SIPush(1));

		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTIf e, Env<Void> env) throws TypingException {
		Label trueLabel = new Label();
		Label endLabel = new Label();

		e.cond.accept(this, env);

		//if the condition is false, jump to the else branch (or to the end if no else branch)
		block.addInstruction(new IIf(e.elseBody != null ? trueLabel : endLabel));

		e.ifBody.accept(this, env);

		//dar pop para dar return a unit no caso em que nao ha else
		if (e.elseBody == null) {
			block.addInstruction(new Pop());
		} else {
			//else branch (if exists)
			block.addInstruction(new Goto(endLabel));
			block.addLabel(trueLabel);
			e.elseBody.accept(this, env);
		}

		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTWhile e, Env<Void> env) throws TypingException {
		Label startLabel = new Label();
		Label endLabel = new Label();

		block.addLabel(startLabel);
		e.condition.accept(this, env);

		//if the condition is false, jump out of the loop
		block.addInstruction(new IIf(endLabel));
		e.body.accept(this, env);

		//dar pop para dar return a unit
		block.addInstruction(new Pop());
		//jump back to the start to re-evaluate the condition
		block.addInstruction(new Goto(startLabel));

		block.addLabel(endLabel);
		return null;
	}

	@Override
	public Void visit(ASTLet e, Env<Void> env) throws TypingException {
		int count = e.varDecls.size();
		Pair<Frame,CompEnv> p = block.beginScope(count);
		Frame f = p.first;
		CompEnv newEnv = p.second;
		String frameName = "frame_" + f.id;
		block.addInstruction(new New(frameName));
		block.addInstruction(new Dup());
		block.addInstruction(new InvokeSpecial(frameName + "/<init>()V"));
		block.addInstruction(new Dup());

		block.addInstruction(new ALoad(0));
		block.addInstruction(new PutField(frameName + "/sl L" + (f.prev == null ? "java/lang/Object" : "frame_" + f.prev.id) + ";"));
		block.addInstruction(new AStore(0));

		int loc = 0;
		for (ASTVarDecl b : e.varDecls) {
			f.addField(b.type);
 			block.addInstruction(new ALoad(0));
			 b.exp.accept(this, env);
			 block.addInstruction(new PutField(frameName + "/loc_" + loc + " " + getTypeDescriptor(b.type)));
			//block.addInstruction(new AStore(0));
			newEnv.put(b.id, loc);
			loc++;
		}
		e.body.accept(this, null);
		block.endScope(f,newEnv);
		block.addInstruction(new ALoad(0));
		block.addInstruction(new GetField(frameName + "/sl L" + (f.prev == null ? "java/lang/Object" : "frame_" + f.prev.id) + ";"));
		block.addInstruction(new AStore(0));
		return null;
	}

	@Override
	public Void visit(ASTId e, Env<Void> env) throws TypingException {
		System.out.println("aqui2");
		block.fetch(e.id, e.type);
		return null;
	}

	@Override
	public Void visit(ASTAssign e, Env<Void> env) throws TypingException {
		e.reference.accept(this, env);
		e.newValue.accept(this, env);
		block.addInstruction(new PutField("ref_" + e.type.toString() + "/value " + getTypeDescriptor(e.type)));
		return null;
	}

	@Override
	public Void visit(ASTNew e, Env<Void> env) throws TypingException {
		String typename = e.type.toString();
		block.addInstruction(new New(typename));
		block.addInstruction(new Dup());
		block.addInstruction(new InvokeSpecial(typename + "/<init>()V"));
		block.addInstruction(new Dup());

		e.arg.accept(this, env);
		Type innerType = ((RefType) e.type).getInner();
		String typeDescriptor = getTypeDescriptor(innerType);
		block.addInstruction(new PutField(typename + "/value " + typeDescriptor));

		Pair<String, String> pair = new Pair<>(typename, typeDescriptor);
		refTypes.add(pair);
		return null;
	}

	public static String createRefString(String type, String innerType) {
		StringBuilder sb = new StringBuilder();
		sb.append(".source ").append(type).append(".j").append("\n");
		sb.append(".class ").append(type).append("\n");
		sb.append(".super java/lang/Object").append("\n").append("\n");
		sb.append(".field public value ").append(innerType).append("\n").append("\n");
		sb.append(".method public <init>()V").append("\n");
		sb.append("aload_0").append("\n");
		sb.append("invokespecial java/lang/Object/<init>()V").append("\n");
		sb.append("return").append("\n");
		sb.append(".end method");
		return sb.toString();
	}

	@Override
	public Void visit(ASTDeref e, Env<Void> env) throws TypingException {
		e.arg.accept(this, env);
		block.addInstruction(new GetField("ref_" + e.type.toString() + "/value " + getTypeDescriptor(e.type)));
		return null;
	}

	@Override
	public Void visit(ASTUnit e, Env<Void> env) throws TypingException {
		block.addInstruction(new IUnit());
		return null;
	}

	@Override
	public Void visit(ASTPrint e, Env<Void> env) throws TypingException {
		e.print.accept(this, env);
		block.addInstruction(new Dup());
		block.addInstruction(new GetStatic("java/lang/System/out", "Ljava/io/PrintStream;"));
		block.addInstruction(new Swap());
		block.addInstruction(new InvokeVirtual("java/io/PrintStream/println(" + getTypeDescriptor(e.type) + ")V"));
		return null;
	}

	@Override
	public Void visit(ASTSeq e, Env<Void> env) throws TypingException {
		e.first.accept(this, env);
		block.addInstruction(new Pop());
		e.second.accept(this, env);
		return null;
	}

	@Override
	public Void visit(ASTFunDef e, Env<Void> env) throws TypingException {
		FunType t = (FunType) e.type;
		ClosureComp closure = block.addClosure(t, e.body, e.params);
		List<Type> types = new ArrayList<>(t.arguments);
		types.add(t.resultType);
		funTypes.add(new Pair<>(t.toString(), types));
		block.addInstruction(new New("closure_" + closure.id));
		block.addInstruction(new Dup());
		block.addInstruction(new ALoad(0));
		block.addInstruction(new PutField("closure_" + closure.id + "/sl Ljava/lang/Object;"));
		closure.endScope();

		return null;
	}

	@Override
	public Void visit(ASTFunCall e, Env<Void> env) throws TypingException {
		block.addInstruction(new CheckCast("fun_" + e.id.getType()));
		for(Exp param : e.params){
			param.accept(this, env);
		}

		StringBuilder types = new StringBuilder();
		for (int i = 0; i < e.params.size(); i++){
			types.append(getTypeDescriptor(e.params.get(i).getType()));
		}
		types.append(")").append(getTypeDescriptor(e.type)).append(" ").append(e.params.size() + 1);
		block.addInstruction(new InvokeInterface("fun_" + e.id.getType() + "/apply(" + types));
		//invokeinterface fun_(int_int)/apply(I)I 2
		return null;
	}

	public static String createFunInterface(String name, List<Type> types) {
		StringBuilder sb = new StringBuilder();
		sb.append(".interface public ").append(name).append("\n");
		sb.append(".super java/lang/Object").append("\n\n");
		sb.append(".method public abstract apply(");
		for (int i = 0; i < types.size()-1; i++){
			sb.append(getTypeDescriptor(types.get(i)));
		}
		sb.append(")").append(getTypeDescriptor(types.get(types.size()-1))).append("\n");
		sb.append(".end method");
		return sb.toString();
	}


	public static BasicBlock codeGen(Exp e) throws TypingException, FileNotFoundException {
		CodeGen cg = new CodeGen();
		Env<Void> globalEnv = new Env<>();
		e.accept(cg, globalEnv);

		for(Pair<String, String> pair : cg.refTypes){
			writeJasminFile(createRefString(pair.first, pair.second), pair.first +".j");
		}
		for(Pair<String, List<Type>> closureInterface : cg.funTypes){
			writeJasminFile(createFunInterface("fun_" + closureInterface.first, closureInterface.second), "fun_" + closureInterface.first + ".j");
		}
		for(ClosureComp closure : cg.block.closures){
			writeJasminFile(closure.toString(), "closure_" + closure.id + ".j");
		}
		for (Frame frame : cg.block.frames){
			writeJasminFile(frame.toString(), "frame_" + frame.id + ".j");
		}

		return cg.block.block;
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
				   """;
		String footer = 
				"""
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

	public static String getTypeDescriptor(Type t) {
		if (t instanceof BoolType) {
			return "Z";
		} else if (t instanceof IntType) {
			return "I";
		} else if (t instanceof RefType innerType) {
			return "L" + innerType + ";";
		} else if (t instanceof FunType funType) {
			return "Ljava/lang/Object;";
		}
		throw new IllegalArgumentException("Unsupported type: " + t);
	}

	private static void writeJasminFile(String content, String filename) throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream(filename));
		out.print(content);
		out.close();
	}

}
