package instructions;

public class CheckCast extends Instruction {

        public CheckCast(String instruction) {
            op = "checkcast";
            args = new String[]{instruction};
        }
}
