package instructions;

public class PutField extends Instruction {

	public PutField(String instruction) {
		op = "putfield";
		args = new String[]{instruction};
	}
}