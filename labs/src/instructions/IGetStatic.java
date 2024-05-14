package instructions;

public class IGetStatic extends Instruction {

	public IGetStatic(String field1, String field2) {
		op = "getstatic";
		args = new String[]{field1, field2};
	}
}