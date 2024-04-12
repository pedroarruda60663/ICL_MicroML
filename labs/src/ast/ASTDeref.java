package ast;

import types.TypingException;

public class ASTDeref implements Exp{
    public Exp arg;

    public ASTDeref(Exp arg) {
        this.arg = arg;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }

}
