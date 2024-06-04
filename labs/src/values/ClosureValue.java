package values;

import ast.Exp;
import symbols.Env;
import symbols.Pair;
import types.Type;

import java.util.List;

public class ClosureValue implements Value {

    public List<Pair<String, Type>> params;
    public Exp body;
    public Env<Value> env;


    public ClosureValue(List<Pair<String, Type>> params, Exp body, Env<Value> env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    @Override
    public IntValue asIntValue() {
        throw new IllegalStateException("Not an IntValue");
    }

    @Override
    public BoolValue asBoolValue() {
        throw new IllegalStateException("Not a BoolValue");
    }

    @Override
    public RefValue asRefValue() {
        throw new IllegalStateException("Not a RefValue");
    }

    @Override
    public ClosureValue asClosureValue() {
        return this;
    }


    public ArrayValues asArrayValue() {throw new IllegalStateException("Not a ArrayValues");}

    @Override
    public DoubleValue asDoubleValue() {
        throw new IllegalStateException("Not a DoubleValue");
    }

    @Override
    public String toString() {
        return "function with " + params.size() + " arguments";
    }

}
