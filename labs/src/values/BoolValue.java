package values;

public class BoolValue implements Value {
	private boolean value;
	
	public BoolValue(boolean value) {
		this.value = value;
	}

	@Override
	public IntValue asIntValue() {
		throw new IllegalStateException("Not an IntValue");
	}

	@Override
	public BoolValue asBoolValue() {
		return this;
	}

	@Override
	public RefValue asRefValue() {
		throw new IllegalStateException("Not a RefValue");
	}

	@Override
	public ClosureValue asClosureValue() { throw new IllegalStateException("Not a ClosureValue");}

	public boolean getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Boolean.toString(value);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BoolValue && value == ((BoolValue)obj).getValue();
	}

}
