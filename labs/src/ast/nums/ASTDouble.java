package ast.nums;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTDouble implements Exp {
    public double value;
    public Type type;
    public ASTDouble(double parseDouble) {
        this.value = parseDouble;
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
