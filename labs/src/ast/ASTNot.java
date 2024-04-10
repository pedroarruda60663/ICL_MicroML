package ast;

import symbols.Env;
import types.TypingException;
import values.Value;

public class ASTNot implements Exp{
    public Exp arg1;

    public ASTNot(Exp arg1) {
        this.arg1 = arg1;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
