package instructions;

public class ILoad extends Instruction {

	public ILoad(int field) {
		op = "iload";
		args = new String[]{String.valueOf(field)};
	}
}