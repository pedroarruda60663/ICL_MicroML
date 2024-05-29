package values;

public interface Value {

    IntValue asIntValue();
    BoolValue asBoolValue();
    RefValue asRefValue();
    ClosureValue asClosureValue();

}
