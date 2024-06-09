package instructions;

public class InvokeInterface extends Instruction {

	public InvokeInterface(String field) {
		op = "invokeinterface";
		args = new String[]{field};
	}
}