package instructions.arrays;

import instructions.Instruction;

public class NewArray extends Instruction {

    public NewArray(String type) {
        op = "newarray";
        args = new String[]{type};
    }
}
