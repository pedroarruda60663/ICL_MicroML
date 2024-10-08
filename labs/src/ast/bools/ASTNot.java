package ast.bools;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTNot implements Exp {
    public Exp arg1;
    public Type type;

    public ASTNot(Exp arg1) {
        this.arg1 = arg1;
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
