package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IGreater extends Instruction {

	public IGreater(Label label) {
		op = "if_icmpgt";
		args = new String[]{label.toString()};
	}
}