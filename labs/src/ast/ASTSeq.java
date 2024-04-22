package ast;


import types.TypingException;

public class ASTSeq implements Exp {
    public Exp first;
    public Exp second;

    public ASTSeq(Exp first, Exp second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
