package instructions;

public class SetField extends Instruction{

        public SetField(String instruction) {
            op = "setfield";
            args = new String[]{instruction};
        }
}
