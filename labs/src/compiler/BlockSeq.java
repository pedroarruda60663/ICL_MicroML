package compiler;

import instructions.Instruction;
import symbols.CompEnv;
import symbols.Pair;
import types.Type;

import java.util.ArrayList;
import java.util.List;

public class BlockSeq {
    List<Frame> frames;
    Frame currFrame;
    BasicBlock block;
    CompEnv env;

    public BlockSeq() {
        this.frames = new ArrayList<>();
        this.block = new BasicBlock();
        env = new CompEnv(null);
    }

    public Pair<Frame, CompEnv> beginScope(int nFields) {
        Frame newFrame = new Frame(frames.size(), currFrame, nFields);
        frames.add(newFrame);
        currFrame = newFrame;
        env = new CompEnv(env);
        return new Pair<>(newFrame, env);
    }

    public void advanceToFrame(Frame f, CompEnv e){
        this.currFrame = f;
        this.env = e;
    }

    public void endScope(Frame f, CompEnv e) {
        this.currFrame = f.prev;
        this.env = e.prev;
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
        //block.addInstruction(new Fetch(depth, fieldIndex, t));

    }
}

