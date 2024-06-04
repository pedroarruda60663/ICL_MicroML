package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class INotEquals extends Instruction {

	public INotEquals(Label label) {
		op = "if_icmpne";
		args = new String[]{label.toString()};
	}
}