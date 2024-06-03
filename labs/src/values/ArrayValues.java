package values;

public class ArrayValues implements Value{

    private Value[] values;

    public ArrayValues(Value[] values) {
        this.values = values;
    }

    public Value[] getValues() {
        return values;
    }

    public Value getValueAt(int index) {
        return values[index];
    }

    public void setValueAt(int index, Value value) {
        values[index] = value;
    }

    public int getSize() {
        return values.length;
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
    public ClosureValue asClosureValue() { throw new IllegalStateException("Not a ClosureValue");}

    @Override
    public ArrayValues asArrayValue() { return this;}
}
