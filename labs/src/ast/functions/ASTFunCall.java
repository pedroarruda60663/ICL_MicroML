package ast.functions;

import ast.Exp;
import types.Type;
import types.TypingException;

import java.util.ArrayList;
import java.util.List;

public class ASTFunCall implements Exp {

    public List<Exp> params;
    public Exp id;
    public Type type;

    public ASTFunCall(Exp body) {
        params = new ArrayList<>();
        this.id = body;
    }

    public void addArg(Exp arg){
        params.add(arg);
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
