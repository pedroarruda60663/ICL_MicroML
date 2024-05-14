package ast.declarations;

import ast.Exp;
import types.Type;
import types.TypingException;

import java.util.List;

public class ASTLet implements Exp {
    public List<ASTVarDecl> varDecls;
    public Exp body;
    public Type type;

    public ASTLet(List<ASTVarDecl> varDecls, Exp body) {
        this.varDecls = varDecls;
        this.body = body;
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
