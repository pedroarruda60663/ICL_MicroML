package instructions.doubles;

import instructions.Instruction;

public class Ldc2w extends Instruction {

	public Ldc2w(double field) {
		op = "ldc2_w";
		args = new String[]{String.valueOf(field)};
	}
}