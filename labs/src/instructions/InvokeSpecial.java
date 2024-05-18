package instructions;

public class InvokeSpecial extends Instruction {

	public InvokeSpecial(String frameName) {
		op = "invokespecial";
		args = new String[]{frameName};
	}
}