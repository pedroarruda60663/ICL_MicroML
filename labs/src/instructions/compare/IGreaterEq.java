package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IGreaterEq extends Instruction {

        public IGreaterEq(Label label) {
            op = "if_icmpge";
            args = new String[]{label.toString()};
        }
}
