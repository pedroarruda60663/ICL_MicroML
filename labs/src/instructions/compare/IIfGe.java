package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IIfGe extends Instruction {

    public IIfGe(Label label) {
        op = "ifge";
        args = new String[]{label.toString()};
    }
}
