package values;

public interface Value {

    boolean isIntValue();
    boolean isBoolValue();
    IntValue asIntValue();
    BoolValue asBoolValue();

}
