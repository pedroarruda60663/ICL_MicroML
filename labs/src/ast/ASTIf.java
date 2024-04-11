package ast;

import types.TypingException;

public class ASTIf implements Exp{

    public Exp cond;
    public Exp ifBody;
    public Exp elseBody;

    public ASTIf(Exp cond, Exp ifBody, Exp elseBody) {
        this.cond = cond;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }
}
