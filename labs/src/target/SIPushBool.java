package target;

public class SIPushBool extends Instruction {

	public SIPushBool(boolean n) {
		op="sipushb";
		args = new String[]{ Boolean.toString(n) } ;
	}
}
