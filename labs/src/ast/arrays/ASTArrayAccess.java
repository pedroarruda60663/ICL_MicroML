package ast.arrays;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTArrayAccess implements Exp {
    public Type type;
    public Exp array;
    public Exp index;

    public ASTArrayAccess(Exp array, Exp index) {
        this.array = array;
        this.index = index;
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
