package ast.references;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTDeref implements Exp {
    public Exp arg;
    public Type type;

    public ASTDeref(Exp arg) {
        this.arg = arg;
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