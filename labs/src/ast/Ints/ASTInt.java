package ast.Ints;

import ast.Exp;
import types.TypingException;

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
