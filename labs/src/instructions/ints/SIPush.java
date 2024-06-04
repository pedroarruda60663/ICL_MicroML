package instructions.ints;

import instructions.Instruction;

public class SIPush extends Instruction {
	public SIPush(int n) {
		op="sipush";
		args = new String[]{ Integer.toString(n) } ; 
	}

}
