package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IIfGt extends Instruction {

    public IIfGt(Label label) {
        op = "ifgt";
        args = new String[]{label.toString()};
    }
}
