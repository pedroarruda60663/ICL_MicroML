package instructions;

public class IEquals extends Instruction {

	public IEquals(Label label) {
		op = "if_icmpeq";
		args = new String[]{label.toString()};
	}
}