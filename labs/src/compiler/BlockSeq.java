package compiler;

import ast.Exp;
import instructions.ALoad;
import instructions.GetField;
import instructions.Instruction;
import instructions.Label;
import symbols.CompEnv;
import symbols.Pair;
import types.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class BlockSeq {

    List<Frame> frames;
    Frame currFrame;
    BasicBlock block;
    CompEnv env;
    List<ClosureComp> closures;

    public BlockSeq() {
        this.frames = new ArrayList<>();
        this.block = new BasicBlock();
        env = new CompEnv(null);
        this.closures = new ArrayList<>();
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

    public ClosureComp addClosure(FunType t, Exp body, List<Pair<String, Type>> params){
        Pair<Frame, CompEnv> pair = this.beginScope(t.arguments.size());
        ClosureComp closure = new ClosureComp(params, closures.size(), t, body, pair, frames);
        closures.add(closure);
        endScope(pair.first, pair.second);
        return closure;
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

        block.addInstruction(new ALoad(0));

        //quando a depth é diferente de 0, ir buscar o valor ao frame necessário
        for (int i = currFrame.id; i > currFrame.id - depth; i--) {
            block.addInstruction(new GetField("frame_" + i + "/sl Lframe_" + (i - 1) + ";"));
        }
        System.out.println("id: " + id + " type: " + t + " currFrame: " + currFrame.id + " size: " + frames.size());
        block.addInstruction(new GetField("frame_" + (currFrame.id - depth) + "/loc_" + fieldIndex + " " + CodeGen.getTypeDescriptor(t)));
    }

    public void addLabel(Label label) {
        block.addLabel(label);
    }

}

