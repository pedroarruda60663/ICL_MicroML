package compiler;

import instructions.ALoad;
import instructions.GetField;
import instructions.Instruction;
import instructions.Label;
import symbols.CompEnv;
import symbols.Pair;
import types.BoolType;
import types.IntType;
import types.Type;
import types.TypingException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class BlockSeq {

    private static final Type BOOLEAN = BoolType.getInstance();
    private static final Type INT = IntType.getInstance();

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

        block.addInstruction(new ALoad(0));

        //quando a depth é diferente de 0, ir buscar o valor ao frame necessário
        for (int i = currFrame.id; i > currFrame.id - depth; i--) {
            block.addInstruction(new GetField("frame_" + i + "/sl Lframe_" + (i - 1) + ";"));
        }

        block.addInstruction(new GetField("frame_" + (currFrame.id - depth) + "/loc_" + fieldIndex + " " + getTypeDescriptor(t)));
    }

    public void addLabel(Label label) {
        block.addLabel(label);
    }

    private String getTypeDescriptor(Type t) {
        if (t instanceof BoolType) {
            return "Z";
        } else if (t instanceof IntType) {
            return "I";
            //case STRING:
            //  return "Ljava/lang/String;";
        }
        throw new IllegalArgumentException("Unsupported type: " + t);
    }

    private static void writeFrameToFile(String frame, String frameName) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream(frameName));
        out.print(frame);
        out.close();
    }
}

