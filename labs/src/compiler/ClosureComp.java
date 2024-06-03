package compiler;

import ast.Exp;
import instructions.*;
import symbols.CompEnv;
import symbols.Env;
import symbols.Pair;
import types.FunType;
import types.Type;
import types.TypingException;

import java.util.List;

public class ClosureComp {

    List<Pair<String, Type>> params;
    FunType type;
    int id;
    Exp body;
    Pair<Frame, CompEnv> frame;

    public ClosureComp(List<Pair<String, Type>> params, int id, FunType type, Exp body, Pair<Frame, CompEnv> frame){
        this.params = params;
        this.id = id;
        this.type = type;
        this.body = body;
        this.frame = frame;
        for (Type argType : type.arguments)
        {
            frame.first.addField(argType);
        }
    }

    public String toString(Exp.Visitor<Void, Env<Void>> v) {
        StringBuilder sb = new StringBuilder();
        sb.append(".class public closure_").append(id).append("\n");
        sb.append(".super java/lang/Object\n");
        sb.append(".implements fun_").append(type).append("\n");
        sb.append(".field public sl Ljava/lang/Object;\n\n");
        sb.append(".method public <init>()V").append("\n");
        sb.append("aload_0").append("\n");
        sb.append("invokenonvirtual java/lang/Object/<init>()V").append("\n");
        sb.append("return").append("\n");
        sb.append(".end method\n\n");

        try {
            sb.append(createApply(v));
        } catch (TypingException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    private String createApply(Exp.Visitor<Void, Env<Void>> v) throws TypingException {
        StringBuilder sb = new StringBuilder();

        sb.append(".method public apply(");
        for (int i = 0; i < type.arguments.size(); i++){
            sb.append(CodeGen.getTypeDescriptor(type.arguments.get(i)));
        }
        sb.append(")").append(CodeGen.getTypeDescriptor(type.resultType)).append("\n");
        sb.append(".limit locals ").append(frame.first.nFields+2).append("\n");
        String frameName = "frame_" + frame.first.id;
        sb.append(new New(frameName)).append("\n");
        sb.append(new Dup()).append("\n");
        sb.append(new InvokeSpecial(frameName + "/<init>()V")).append("\n");
        sb.append(new Dup()).append("\n");
        sb.append(new ALoad(0)).append("\n");
        sb.append(new GetField("closure_"+ id + "/sl L" +"java/lang/Object;")).append("\n");//+ (f.prev == null ? "java/lang/Object" : "frame_" + f.prev.id) + ";"));
        sb.append(new PutField(frameName + "/sl L" +"java/lang/Object;")).append("\n");//+ (f.prev == null ? "java/lang/Object" : "frame_" + f.prev.id) + ";"));
        sb.append(new Dup()).append("\n");
        int i = 1;
        for (Pair<String, Type> t : params){
            frame.second.put(t.first, i-1);
            sb.append(new ALoad(i)).append("\n");
            sb.append(new PutField(frameName + "/loc_" + (i - 1))).append(" ").append(CodeGen.getTypeDescriptor(t.second)).append("\n");
            i++;
        }
        sb.append(new AStore(0)).append("\n");
        body.accept(v, null);

        sb.append("return\n");
        sb.append(".end method");
        return sb.toString();
    }

}
