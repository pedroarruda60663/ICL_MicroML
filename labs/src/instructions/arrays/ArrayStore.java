package instructions.arrays;

import instructions.Instruction;

public class ArrayStore extends Instruction {

    public ArrayStore() {
        op = "iastore";
        args = new String[]{};
    }
}
