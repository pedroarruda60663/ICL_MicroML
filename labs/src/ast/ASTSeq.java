package ast;


import types.Type;
import types.TypingException;

public class ASTSeq implements Exp {
    public Exp first;
    public Exp second;
    public Type type;

    public ASTSeq(Exp first, Exp second) {
        this.first = first;
        this.second = second;
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
