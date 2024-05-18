package instructions;

public class AStore extends Instruction {

	public AStore(int field) {
		op = "astore";
		args = new String[]{String.valueOf(field)};
	}
}