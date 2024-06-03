package compiler;

import types.FunType;

public class ClosureComp {

    FunType type;
    int id;

    public ClosureComp(int id, FunType type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(".class public closure_").append(id).append("\n");
        sb.append(".super java/lang/Object\n");
        sb.append(".implements ").append(type).append("\n");
        sb.append(".field public sl Ljava/lang/Object;\n\n");
        sb.append(".method public <init>()V").append("\n");
        sb.append("aload_0").append("\n");
        sb.append("invokenonvirtual java/lang/Object/<init>()V").append("\n");
        sb.append("return").append("\n");
        sb.append(".end method");
        return sb.toString();
    }

}
