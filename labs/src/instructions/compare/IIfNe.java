package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IIfNe extends Instruction {

    public IIfNe(Label label) {
        op = "ifne";
        args = new String[]{label.toString()};
    }
}
