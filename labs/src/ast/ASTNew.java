package ast;

import types.TypingException;

public class ASTNew implements Exp{
    public Exp arg;

    public ASTNew(Exp arg) {
        this.arg = arg;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }

}
