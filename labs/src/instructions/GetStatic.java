package instructions;

public class GetStatic extends Instruction {

	public GetStatic(String field1, String field2) {
		op = "getstatic";
		args = new String[]{field1, field2};
	}
}