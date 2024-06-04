package instructions.invoke_field;

import instructions.Instruction;

public class GetField extends Instruction {

	public GetField(String instruction) {
		op = "getfield";
		args = new String[]{instruction};
	}
}