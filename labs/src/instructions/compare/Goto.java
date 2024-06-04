package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class Goto extends Instruction {

    public Goto(Label label) {
        op = "goto";
        args = new String[]{label.toString()};
    }
}
