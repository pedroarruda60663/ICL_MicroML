package instructions.invoke_field;

import instructions.Instruction;

public class InvokeStatic extends Instruction {

	public InvokeStatic(String instruction) {
		op = "invokestatic";
		args = new String[]{instruction};
	}
}