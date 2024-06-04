package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class ILess extends Instruction {

	public ILess(Label label) {
		op = "if_icmplt";
		args = new String[]{label.toString()};
	}
}