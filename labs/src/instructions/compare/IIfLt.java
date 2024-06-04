package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IIfLt extends Instruction {

    public IIfLt(Label label) {
        op = "iflt";
        args = new String[]{label.toString()};
    }
}
