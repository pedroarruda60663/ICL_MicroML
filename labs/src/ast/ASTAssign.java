package ast;


import types.TypingException;

public class ASTAssign implements Exp {
    public Exp reference;
    public Exp newValue;

    public ASTAssign(Exp reference, Exp newValue) {
        this.reference = reference;
        this.newValue = newValue;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
