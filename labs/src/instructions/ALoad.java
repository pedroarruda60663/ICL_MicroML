package instructions;

public class ALoad extends Instruction {

	public ALoad(int field) {
		op = "aload";
		args = new String[]{String.valueOf(field)};
	}
}