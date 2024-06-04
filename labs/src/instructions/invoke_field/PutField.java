package instructions.invoke_field;

import instructions.Instruction;

public class PutField extends Instruction {

	public PutField(String instruction) {
		op = "putfield";
		args = new String[]{instruction};
	}
}