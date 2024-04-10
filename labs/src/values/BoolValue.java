package values;

public class BoolValue implements Value {
	private boolean value;
	
	public BoolValue(boolean value) {
		this.value = value;
	}

	@Override
	public boolean isIntValue() {
		return false;
	}

	@Override
	public boolean isBoolValue() {
		return true;
	}

	@Override
	public IntValue asIntValue() {
		throw new IllegalStateException("Not an IntValue");
	}

	@Override
	public BoolValue asBoolValue() {
		return this;
	}

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
