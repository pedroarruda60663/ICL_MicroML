package ast.arrays;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTArrayAssign implements Exp {
    public Type type;
    public Exp array;
    public Exp index;
    public Exp newValue;

    public ASTArrayAssign(Exp array, Exp index, Exp newValue) {
        this.array = array;
        this.index = index;
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
