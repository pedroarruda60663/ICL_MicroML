package ast.ints;

import ast.Exp;
import types.TypingException;

public class ASTNum implements Exp {
    public int value;
    public ASTNum(int parseInt) {
        this.value = parseInt;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return null;
    }
}
