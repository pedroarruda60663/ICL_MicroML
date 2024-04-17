package values;

public class RefValue implements Value {
    @Override
    public boolean isIntValue() {
        return false;
    }

    @Override
    public boolean isBoolValue() {
        return false;
    }

    @Override
    public boolean isRefValue() {
        return true;
    }

    @Override
    public IntValue asIntValue() {
        throw new IllegalStateException("Not a IntValue");
    }

    @Override
    public BoolValue asBoolValue() {
        throw new IllegalStateException("Not a BoolValue");
    }

    @Override
    public RefValue asRefValue() {
        return this;
    }
}
