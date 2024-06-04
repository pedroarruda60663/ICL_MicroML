package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IIf extends Instruction {

    public IIf(Label label) {
        op = "ifeq";
        args = new String[]{label.toString()};
    }
}
