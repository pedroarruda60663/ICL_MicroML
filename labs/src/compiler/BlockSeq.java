package compiler;

import instructions.Instruction;
import symbols.CompEnv;
import symbols.Pair;
import types.Type;

import java.util.ArrayList;
import java.util.List;

public class BlockSeq {
    List<Frame> frames = new ArrayList<>();
    Frame currFrame;
    BasicBlock block = new BasicBlock();
    CompEnv env;

    public BlockSeq() {
        env = new CompEnv(null);
    }

    public Pair<Frame, CompEnv> beginScope(int nFields) {
        Frame newFrame = new Frame(frames.size(), currFrame);
        currFrame = newFrame;
        frames.add(newFrame);
        env = new CompEnv(env);
        for (int i = 0; i < nFields; i++) {
           // newFrame.addField(new Type());
        }
        return new Pair<>(newFrame, env);
    }

    public void endScope() {
        currFrame = currFrame.prev;
        env = env.prev;
    }

    public void addInstruction(Instruction i) {
        block.addInstruction(i);
    }

    public void fetch(String id, Type t) {
        Pair<Integer, Integer> loc = env.find(id);
        if (loc == null) {
            throw new RuntimeException("Identifier not found: " + id);
        }
        int depth = loc.first;
        int fieldIndex = loc.second;
        // Add appropriate instructions to fetch this variable based on depth and fieldIndex
    }
}

