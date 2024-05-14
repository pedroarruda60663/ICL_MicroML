package ast;

import types.Type;
import types.TypingException;

public class ASTUnit implements Exp{
    public Type type;
    public ASTUnit() {}

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }

}
