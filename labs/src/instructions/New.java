package instructions;

public class New extends Instruction {

	public New(String frameName) {
		op = "new";
		args = new String[]{frameName};
	}
}