package instructions.invoke_field;

import instructions.Instruction;

public class InvokeVirtual extends Instruction {

	public InvokeVirtual(String field) {
		op = "invokevirtual";
		args = new String[]{field};
	}
}