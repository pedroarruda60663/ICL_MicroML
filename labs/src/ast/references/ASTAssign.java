package ast.references;


import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTAssign implements Exp {
    public Exp reference;
    public Exp newValue;
    public Type type;

    public ASTAssign(Exp reference, Exp newValue) {
        this.reference = reference;
        this.newValue = newValue;
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
