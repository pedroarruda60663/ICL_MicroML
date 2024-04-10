package ast;

import symbols.Env;
import types.TypingException;
import values.Value;

import java.util.List;

public class ASTId implements Exp{
    public String id;

    public ASTId(String id) {
        this.id = id;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }

}
