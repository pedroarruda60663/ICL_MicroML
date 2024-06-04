package values;

public class RefValue implements Value {
	private Value value;

	public RefValue(Value value) {
		this.value = value;
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
		return this;
	}

	@Override
	public ClosureValue asClosureValue() {
		throw new IllegalStateException("Not a ClosureValue");
	}

	@Override
	public ArrayValues asArrayValue() {throw new IllegalStateException("Not a ArrayValues");}
	@Override
	public DoubleValue asDoubleValue() {
		throw new IllegalStateException("Not a DoubleValue");
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value newValue) { this.value = newValue; }

	@Override
	public String toString() {
		return "ref("+value.toString()+")";
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof RefValue && value == ((RefValue)obj).getValue();
	}

}
