package ast.nums;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTInt implements Exp {
    public int value;
    public Type type;
    public ASTInt(int parseInt) {
        this.value = parseInt;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
