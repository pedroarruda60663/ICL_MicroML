package target;

public class ILessEq extends Instruction {

    public ILessEq(Label label) {
        op = "if_icmple";
        args = new String[]{label.toString()};
    }
}
