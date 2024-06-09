package compiler;

import ast.Exp;
import instructions.*;
import instructions.invoke_field.GetField;
import instructions.invoke_field.InvokeSpecial;
import instructions.invoke_field.PutField;
import symbols.CompEnv;
import symbols.Env;
import symbols.Pair;
import types.*;

import java.io.FileNotFoundException;
import java.util.List;

public class ClosureComp {

    List<Pair<String, Type>> params;
    FunType type;
    int id;
    Exp body;
    Pair<Frame, CompEnv> frame;
    CodeGen cg;
    Env<Void> globalEnv;

    public ClosureComp(List<Pair<String, Type>> params, int id, FunType type, Exp body, Pair<Frame, CompEnv> frame, List<Frame> frames){
        this.params = params;
        this.id = id;
        this.type = type;
        this.body = body;
        this.frame = frame;
        for (Type argType : type.arguments)
        {
            frame.first.addField(argType);
        }
        cg = new CodeGen();
        cg.block.currFrame = this.frame.first;
        cg.block.env = this.frame.second;
        globalEnv = new Env<>();
        cg.block.frames = frames;
    }

    public void endScope() {
        cg.block.endScope(frame.first, frame.second);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(".class public closure_").append(id).append("\n");
        sb.append(".super java/lang/Object\n");
        sb.append(".implements fun_").append(type).append("\n");
        sb.append(".field public sl L").append(frame.first.prev == null ? "java/lang/Object;" : "frame_" + frame.first.prev.id).append(";").append("\n");
        sb.append(".method public <init>()V").append("\n");
        sb.append("aload_0").append("\n");
        sb.append("invokenonvirtual java/lang/Object/<init>()V").append("\n");
        sb.append("return").append("\n");
        sb.append(".end method\n\n");

        try {
            sb.append(createApply());
        } catch (TypingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    private String createApply() throws TypingException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        sb.append(".method public apply(");
        for (int i = 0; i < type.arguments.size(); i++){
            if(!(type.arguments.get(i) instanceof UnitType)) {
                sb.append(CodeGen.getTypeDescriptor(type.arguments.get(i)));
            }
        }
        sb.append(")").append(CodeGen.getTypeDescriptor(type.resultType)).append("\n");
        sb.append(".limit locals ").append(frame.first.nFields+3).append("\n");
        sb.append(".limit stack ").append(frame.first.nFields+4).append("\n");
        String frameName = "frame_" + frame.first.id;
        sb.append(new New(frameName)).append("\n");
        sb.append(new Dup()).append("\n");
        sb.append(new InvokeSpecial(frameName + "/<init>()V")).append("\n");
        sb.append(new Dup()).append("\n");
        sb.append(new ALoad(0)).append("\n");
        sb.append(new GetField("closure_"+ id + "/sl L" + (frame.first.prev == null ? "java/lang/Object;" : "frame_" + frame.first.prev.id) + ";")).append("\n");
        sb.append(new PutField(frameName + "/sl L" + (frame.first.prev == null ? "java/lang/Object;" : "frame_" + frame.first.prev.id) + ";")).append("\n");

        int i = 1;
        int arg = 1;
        for (Pair<String, Type> t : params){
                frame.second.put(t.first, i-1);
            if(!(t.second instanceof UnitType)) {
                sb.append(new Dup()).append("\n");
                sb.append(getFunctionArgInstructions(t.second, arg)).append("\n");
                sb.append(new PutField(frameName + "/loc_" + (i - 1))).append(" ").append(CodeGen.getTypeDescriptor(t.second)).append("\n");
                arg++;
            }
            i++;
        }

        sb.append(new AStore(0)).append("\n");
        body.accept(cg, globalEnv);
        sb.append(cg.block.block.build());

        sb.append(getFunctionReturn(type.resultType)).append("\n");
        sb.append(".end method");
        return sb.toString();
    }

    private Instruction getFunctionArgInstructions(Type t, int position){
        if (t instanceof BoolType) {
            return new ILoad(position);
        } else if (t instanceof IntType) {
            return new ILoad(position);
        } else if (t instanceof DoubleType) {
            return new DLoad(position);
        } else if (t instanceof RefType) {
            return new ALoad(position);
        } else if (t instanceof ArrayType) {
            return new ALoad(position);
        } else if (t instanceof FunType) {
            return new ALoad(position);
        }
        throw new IllegalArgumentException("Unsupported type: " + t);
    }

    private String getFunctionReturn(Type t){
        if (t instanceof BoolType) {
            return "ireturn";
        } else if (t instanceof IntType) {
            return "ireturn";
        } else if (t instanceof DoubleType) {
            return "dreturn";
        } else if (t instanceof RefType) {
            return "areturn";
        } else if (t instanceof ArrayType) {
            return "areturn";
        } else if (t instanceof FunType) {
            return "areturn";
        } else if (t instanceof UnitType) {
            return "return";
        }
        throw new IllegalArgumentException("Unsupported type: " + t);
    }

}
