package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IIfLe extends Instruction {

    public IIfLe(Label label) {
        op = "ifle";
        args = new String[]{label.toString()};
    }
}
