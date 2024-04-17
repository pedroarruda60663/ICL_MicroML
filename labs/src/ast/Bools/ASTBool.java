package ast.Bools;

import ast.Exp;
import types.TypingException;

public class ASTBool implements Exp {
    public boolean value;
    public ASTBool(boolean parseBool) {
        this.value = parseBool;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
