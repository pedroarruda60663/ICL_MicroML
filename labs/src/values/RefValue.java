package values;

public class RefValue implements Value {
	private Value value;

	public RefValue(Value value) {
		this.value = value;
	}

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
