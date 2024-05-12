package target;

public class IGetStatic extends Instruction {

	public IGetStatic(String field) {
		op = "getstatic";
		args = new String[]{field};
	}
}