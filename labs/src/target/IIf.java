package target;

public class IIf extends Instruction{

    public IIf(Label label) {
        op = "ifeq";
        args = new String[]{label.toString()};
    }
}
