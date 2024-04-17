package values;

public interface Value {

    boolean isIntValue();
    boolean isBoolValue();
    boolean isRefValue();
    IntValue asIntValue();
    BoolValue asBoolValue();
    RefValue asRefValue();

}
