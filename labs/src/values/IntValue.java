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
	public boolean isIntValue() {
		return true;
	}

	@Override
	public boolean isBoolValue() {
		return false;
	}

	@Override
	public IntValue asIntValue() {
		return this;
	}

	@Override
	public BoolValue asBoolValue() {
		throw new IllegalStateException("Not a BoolValue");
	}
	
}
