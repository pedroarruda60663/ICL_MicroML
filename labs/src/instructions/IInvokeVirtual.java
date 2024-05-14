package instructions;

public class IInvokeVirtual extends Instruction {

	public IInvokeVirtual(String field) {
		op = "invokevirtual";
		args = new String[]{field};
	}
}