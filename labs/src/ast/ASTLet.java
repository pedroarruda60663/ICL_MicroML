package ast;

import symbols.Env;
import types.TypingException;
import values.Value;

import java.util.List;

public class ASTLet implements Exp{
    public List<ASTVarDecl> varDecls;
    public Exp body;

    public ASTLet(List<ASTVarDecl> varDecls, Exp body) {
        this.varDecls = varDecls;
        this.body = body;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E env) throws TypingException {
        return v.visit(this, env);
    }

}
