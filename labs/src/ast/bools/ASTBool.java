package ast.bools;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTBool implements Exp {
    public boolean value;
    public Type type;

    public ASTBool(boolean parseBool) {
        this.value = parseBool;
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
