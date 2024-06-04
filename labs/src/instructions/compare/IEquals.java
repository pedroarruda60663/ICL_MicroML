package instructions.compare;

import instructions.Instruction;
import instructions.Label;

public class IEquals extends Instruction {

	public IEquals(Label label) {
		op = "if_icmpeq";
		args = new String[]{label.toString()};
	}
}