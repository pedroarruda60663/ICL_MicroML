package ast.arrays;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTNewArray implements Exp {
    public Type type;
    public Type elementType;
    public Exp size;

    public ASTNewArray(Type elementType, Exp size) {
        this.elementType = elementType;
        this.size = size;
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
