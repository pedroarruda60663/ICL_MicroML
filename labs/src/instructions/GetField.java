package instructions;

public class GetField extends Instruction {

	public GetField(String instruction) {
		op = "getfield";
		args = new String[]{instruction};
	}
}