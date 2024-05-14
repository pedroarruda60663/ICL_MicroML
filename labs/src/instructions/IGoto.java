package instructions;

public class IGoto extends Instruction {

    public IGoto(Label label) {
        op = "goto";
        args = new String[]{label.toString()};
    }
}
