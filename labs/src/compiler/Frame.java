package compiler;

import types.Type;

import java.util.List;
import java.util.ArrayList;

public class Frame {
    int nFields;
    List<Type> types = new ArrayList<>();
    Frame prev;
    int id;

    public Frame(int id, Frame prev) {
        this.id = id;
        this.prev = prev;
    }

    public void addField(Type type) {
        types.add(type);
        nFields++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Frame ID: " + id + "\nFields:\n");
        for (int i = 0; i < nFields; i++) {
            sb.append("Field ").append(i).append(": Type ").append(types.get(i)).append("\n");
        }
        return sb.toString();
    }
}
