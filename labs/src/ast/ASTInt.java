package ast;

import symbols.Env;
import types.TypingException;
import values.Value;

public class ASTInt implements Exp {
    public int value;
    public ASTInt(int parseInt) {
        this.value = parseInt;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
