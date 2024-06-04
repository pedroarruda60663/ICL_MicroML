package instructions.invoke_field;

import instructions.Instruction;

public class InvokeSpecial extends Instruction {

	public InvokeSpecial(String frameName) {
		op = "invokespecial";
		args = new String[]{frameName};
	}
}