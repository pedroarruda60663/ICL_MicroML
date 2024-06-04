package values;

public class IntValue implements Value {
	private int value;
	
	public IntValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof IntValue && value == ((IntValue)obj).getValue();
	}

	@Override
	public IntValue asIntValue() {
		return this;
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
	public ClosureValue asClosureValue() {throw new IllegalStateException("Not a ClosureValue");}

	@Override
	public ArrayValues asArrayValue() {throw new IllegalStateException("Not a ArrayValues");}

	@Override
	public DoubleValue asDoubleValue() {
		throw new IllegalStateException("Not a DoubleValue");
	}

}
