package ast.nums;

import ast.Exp;
import types.*;

public class ASTAdd implements Exp {
    public Exp arg1;
    public Exp arg2;
    public Type type;

    public ASTAdd(Exp e1, Exp e2) {
        this.arg1 =  e1;
        this.arg2 = e2;
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
