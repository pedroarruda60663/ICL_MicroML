package ast;

import types.TypingException;

public class ASTUnit implements Exp{
    public ASTUnit() {}

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }

}
