package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class ILessEq extends Instruction {

    public ILessEq(Label label) {
        op = "if_icmple";
        args = new String[]{label.toString()};
    }
}
