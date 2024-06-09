package instructions;

public class DLoad extends Instruction {

	public DLoad(int field) {
		op = "dload";
		args = new String[]{String.valueOf(field)};
	}
}