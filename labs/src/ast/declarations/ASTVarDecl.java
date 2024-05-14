package ast.declarations;

import ast.Exp;
import types.Type;
import types.TypingException;

public class ASTVarDecl{
    public String id;
    public Exp exp;
    public Type type;

    public ASTVarDecl(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

}
