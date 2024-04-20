package ast;

import types.TypingException;

public class ASTPrint implements Exp{

    public Exp print;

    public ASTPrint(Exp print) {
        this.print = print;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
