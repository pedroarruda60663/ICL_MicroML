package ast;

import types.TypingException;

public class ASTVarDecl{
    public String id;
    public Exp exp;

    public ASTVarDecl(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

}
