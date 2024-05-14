package ast;


import types.Type;
import types.TypingException;

public class ASTWhile implements Exp {
    public Exp condition;
    public Exp body;
    public Type type;

    public ASTWhile(Exp condition, Exp body) {
        this.condition = condition;
        this.body = body;
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
