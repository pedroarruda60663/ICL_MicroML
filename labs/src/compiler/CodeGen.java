package compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
import symbols.CompEnv;
import symbols.Env;
import instructions.*;
import instructions.ints.*;
import instructions.doubles.*;
import instructions.arrays.*;
import instructions.compare.*;
import instructions.invoke_field.*;
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
		handleArithmeticOperation(e.arg1, e.arg2, new IAdd(), new DAdd(), env);
	    return null;
	}

	@Override
	public Void visit(ASTMult e, Env<Void> env) throws TypingException {
		handleArithmeticOperation(e.arg1, e.arg2, new IMul(), new DMul(), env);
		return null;
	}


	public Void visit(ASTDiv e, Env<Void> env) throws TypingException {
		handleArithmeticOperation(e.arg1, e.arg2, new IDiv(), new DDiv(), env);
		return null;
	}

	@Override
	public Void visit(ASTSub e, Env<Void> env) throws TypingException {
		handleArithmeticOperation(e.arg1, e.arg2, new ISub(), new DSub(), env);
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
		if (e.arg1.getType().isDoubleType() || e.arg2.getType().isDoubleType()) {
			handleDoubles(e.arg1, e.arg2, new DCmpg(), env);
			handleComparisons(new IIfLt(new Label()));
		}
		else
		{
			e.arg1.accept(this, env);
			e.arg2.accept(this, env);
			handleComparisons(new ILess(new Label()));
		}
		return null;
	}

	@Override
	public Void visit(ASTGreater e, Env<Void> env) throws TypingException {
		if (e.arg1.getType().isDoubleType() || e.arg2.getType().isDoubleType()) {
			handleDoubles(e.arg1, e.arg2, new DCmpg(), env);
			handleComparisons(new IIfGt(new Label()));
		}
		else
		{
			e.arg1.accept(this, env);
			e.arg2.accept(this, env);
			handleComparisons(new IGreater(new Label()));
		}
		return null;
	}

	@Override
	public Void visit(ASTEquals e, Env<Void> env) throws TypingException {
		if (e.arg1.getType().isDoubleType() || e.arg2.getType().isDoubleType()) {
			handleDoubles(e.arg1, e.arg2, new DCmpg(), env);
			handleComparisons(new IIf(new Label()));
		}
		else
		{
			e.arg1.accept(this, env);
			e.arg2.accept(this, env);
			handleComparisons(new IEquals(new Label()));
		}
		return null;
	}

	@Override
	public Void visit(ASTNotEquals e, Env<Void> env) throws TypingException {
		if (e.arg1.getType().isDoubleType() || e.arg2.getType().isDoubleType()) {
			handleDoubles(e.arg1, e.arg2, new DCmpg(), env);
			handleComparisons(new IIfNe(new Label()));
		}
		else
		{
			e.arg1.accept(this, env);
			e.arg2.accept(this, env);
			handleComparisons(new INotEquals(new Label()));
		}
		return null;
	}

	@Override
	public Void visit(ASTLessEq e, Env<Void> env) throws TypingException {
		if (e.arg1.getType().isDoubleType() || e.arg2.getType().isDoubleType()) {
			handleDoubles(e.arg1, e.arg2, new DCmpg(), env);
			handleComparisons(new IIfLe(new Label()));
		}
		else
		{
			e.arg1.accept(this, env);
			e.arg2.accept(this, env);
			handleComparisons(new ILessEq(new Label()));
		}
		return null;
	}

	@Override
	public Void visit(ASTGreaterEq e, Env<Void> env) throws TypingException {
		if (e.arg1.getType().isDoubleType() || e.arg2.getType().isDoubleType()) {
			handleDoubles(e.arg1, e.arg2, new DCmpg(), env);
			handleComparisons(new IIfGe(new Label()));
		}
		else
		{
			e.arg1.accept(this, env);
			e.arg2.accept(this, env);
			handleComparisons(new IGreaterEq(new Label()));
		}
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

		if (e.elseBody != null) {
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

		//jump back to the start to re-evaluate the condition
		block.addInstruction(new Goto(startLabel));
		block.addLabel(endLabel);

		//dar pop quando o que fica no stack não é unit
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
		//block.addInstruction(new IUnit());
		return null;
	}

	@Override
	public Void visit(ASTPrint e, Env<Void> env) throws TypingException {
		e.print.accept(this, env);
		block.addInstruction(new InvokeVirtual("java/io/PrintStream/println(" + getTypeDescriptor(e.type) + ")V"));
		return null;
	}

	@Override
	public Void visit(ASTSeq e, Env<Void> env) throws TypingException {
		e.first.accept(this, env);
		//dar pop quando o first não é unit
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
		block.addInstruction(new InvokeSpecial("closure_" + closure.id + "/<init>()V"));
		block.addInstruction(new Dup());
		block.addInstruction(new ALoad(0));
		block.addInstruction(new PutField("closure_" + closure.id + "/sl L" + (closure.frame.first.prev == null ? "java/lang/Object;" : "frame_" + closure.frame.first.prev.id) + ";"));
		//closure.endScope();

		return null;
	}

	@Override
	public Void visit(ASTFunCall e, Env<Void> env) throws TypingException {
		e.id.accept(this, env);
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

	@Override
	public Void visit(ASTNewArray e, Env<Void> env) throws TypingException {
		e.size.accept(this, env);
		if(e.elementType.equals("int")) {
			block.addInstruction(new NewArray("int"));
		} else if (e.elementType.equals("bool")){
			block.addInstruction(new NewArray("byte"));
		}
		else {
			throw new TypingException("Unsupported array type : " + e.elementType);
		}
		return null;
	}

	@Override
	public Void visit(ASTArrayAssign e, Env<Void> env) throws TypingException {
		e.array.accept(this, env);
		e.index.accept(this, env);
		e.newValue.accept(this, env);
		if(((ArrayType) e.array.getType()).elementType instanceof IntType){
			block.addInstruction(new ArrayStoreInt());
		} else if(((ArrayType) e.array.getType()).elementType instanceof BoolType){
			block.addInstruction(new ArrayStoreBool());
		}
		else {
			throw new TypingException("Unsupported array type: " + e.array.getType());
		}
		return null;
	}

	@Override
	public Void visit(ASTArrayAccess e, Env<Void> env) throws TypingException {
		e.array.accept(this, env);
		e.index.accept(this, env);
		if(((ArrayType) e.array.getType()).elementType instanceof IntType){
			block.addInstruction(new ArrayLoadInt());
		} else if(((ArrayType) e.array.getType()).elementType instanceof BoolType){
			block.addInstruction(new ArrayLoadBool());
		}
		else {
			throw new TypingException("Unsupported array type:" + e.array.getType());
		}
		return null;
	}

	@Override
	public Void visit(ASTDouble e, Env<Void> env) throws TypingException {
		block.addInstruction(new Ldc2w(e.value));
		return null;
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
		for(ClosureComp closure : cg.block.closures) {
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
					   getstatic java/lang/System/out Ljava/io/PrintStream;				          
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

	private static void writeJasminFile(String content, String fileName) throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream(fileName));
		out.print(content);
		out.close();
	}

	private void handleComparisons(Instruction op) throws TypingException {
		Label trueLabel = new Label();
		Label endLabel = new Label();
		op.args = new String[]{trueLabel.toString()};
		block.addInstruction(op);
		block.addInstruction(new SIPush(0));
		block.addInstruction(new Goto(endLabel));
		block.addLabel(trueLabel);
		block.addInstruction(new SIPush(1));
		block.addLabel(endLabel);
	}

	private void handleArithmeticOperation(Exp arg1, Exp arg2, Instruction intOp, Instruction doubleOp, Env<Void> env) throws TypingException {
		if (arg1.getType().isDoubleType() || arg2.getType().isDoubleType()) {
			handleDoubles(arg1, arg2, doubleOp, env);
		} else {
			arg1.accept(this, env);
			arg2.accept(this, env);
			block.addInstruction(intOp);
		}
	}

	private void handleDoubles(Exp arg1, Exp arg2, Instruction doubleOp, Env<Void> env) throws TypingException {
		if (!arg1.getType().isDoubleType()) {
			arg1.accept(this, env);
			block.addInstruction(new I2D());
			arg2.accept(this, env);
		}
		if (!arg2.getType().isDoubleType()) {
			arg1.accept(this, env);
			arg2.accept(this, env);
			block.addInstruction(new I2D());
		}
		if (arg1.getType().isDoubleType() && arg2.getType().isDoubleType()) {
			arg1.accept(this, env);
			arg2.accept(this, env);
		}
		block.addInstruction(doubleOp);
	}

	public static String getTypeDescriptor(Type t) {
		if (t instanceof BoolType) {
			return "Z";
		} else if (t instanceof IntType) {
			return "I";
		} else if (t instanceof DoubleType) {
			return "D";
		} else if (t instanceof RefType) {
			RefType innerType = (RefType) t;
			return "L" + innerType.toString() + ";";
		} else if (t instanceof ArrayType) {
			Type innerType = ((ArrayType) t).elementType;
			return "[" + getArrayTypeDescriptor(innerType);
		} else if (t instanceof FunType funType) {
				return "Lfun_" + funType + ";";
		}
		throw new IllegalArgumentException("Unsupported type: " + t);
	}

	public static String getArrayTypeDescriptor(Type t){
		if (t instanceof BoolType) {
			return "B";
		} else if (t instanceof IntType) {
			return "I";
		}
		throw new IllegalArgumentException("Unsupported type: " + t);
	}


	private static void writeFrameToFile(String frame, String frameName) throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream(frameName));
		out.print(frame);
		out.close();
	}

}
