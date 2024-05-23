package ast.functions;

import ast.Exp;
import symbols.Pair;
import types.Type;
import types.TypingException;

import java.util.List;

public class ASTFunDef implements Exp {

    public List<Pair<String, Type>> params;
    public Exp body;
    public Type type;

    public ASTFunDef(List<Pair<String, Type>> params, Exp body) {
        this.params = params;
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
