package ast;

import symbols.Env;
import types.TypingException;
import values.Value;

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
