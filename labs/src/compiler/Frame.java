package compiler;

import types.BoolType;
import types.IntType;
import types.RefType;
import types.Type;

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
        //nFields++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(".class public frame_").append(id).append("\n");
        sb.append(".super java/lang/Object\n").append("\n");
        sb.append(".field public sl L").append(prev == null ? "java/lang/Object" : "frame_" + prev.id).append(";\n");
        for (int i = 0; i < nFields; i++) {
            sb.append(".field public loc_").append(i).append(" ").append(getTypeDescriptor(types.get(i))).append("\n");
        }
        sb.append(".method public <init>()V").append("\n");
        sb.append("aload_0").append("\n");
        sb.append("invokenonvirtual java/lang/Object/<init>()V").append("\n");
        sb.append("return").append("\n");
        sb.append(".end method");
        return sb.toString();
    }

    private String getTypeDescriptor(Type t) {
        if (t instanceof BoolType) {
            return "Z";
        } else if (t instanceof IntType) {
            return "I";
        } else if (t instanceof RefType) {
            RefType innerType = (RefType) t;
            return "L" + innerType.toString() + ";";
        }
        throw new IllegalArgumentException("Unsupported type: " + t);
    }

}

