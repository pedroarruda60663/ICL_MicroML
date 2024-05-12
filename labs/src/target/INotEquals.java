package target;

public class INotEquals extends Instruction {

	public INotEquals(Label label) {
		op = "if_icmpne";
		args = new String[]{label.toString()};
	}
}