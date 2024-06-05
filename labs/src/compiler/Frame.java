package compiler;

import types.*;

import java.util.List;
import java.util.ArrayList;

public class Frame {
    int nFields;
    List<Type> types;
    Frame prev;
    int id;

    public Frame(int id, Frame prev, int nFields) {
        this.id = id;
        this.prev = prev;
        this.nFields = nFields;
        types = new ArrayList<>(nFields);
    }

    public void addField(Type type) {
        types.add(type);
        System.out.println(type);
        //nFields++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(".class public frame_").append(id).append("\n");
        sb.append(".super java/lang/Object\n").append("\n");
        sb.append(".field public sl L").append(prev == null ? "java/lang/Object" : "frame_" + prev.id).append(";\n");
        for (int i = 0; i < nFields; i++) {
            sb.append(".field public loc_").append(i).append(" ").append(CodeGen.getTypeDescriptor(types.get(i))).append("\n");
        }
        sb.append(".method public <init>()V").append("\n");
        sb.append("aload_0").append("\n");
        sb.append("invokenonvirtual java/lang/Object/<init>()V").append("\n");
        sb.append("return").append("\n");
        sb.append(".end method");
        return sb.toString();
    }

}

