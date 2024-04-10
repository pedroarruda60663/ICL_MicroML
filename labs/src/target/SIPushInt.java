package target;

public class SIPushInt extends Instruction {
	public SIPushInt(int n) {
		op="sipushi";
		args = new String[]{ Integer.toString(n) } ; 
	}

}
