package values;

public class DoubleValue implements Value {
	private double value;

	public DoubleValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof DoubleValue && value == ((DoubleValue)obj).getValue();
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
	public ClosureValue asClosureValue() {throw new IllegalStateException("Not a ClosureValue");}

	@Override
	public ArrayValues asArrayValue() {throw new IllegalStateException("Not a ArrayValues");}

	@Override
	public DoubleValue asDoubleValue() {
		return this;
	}

}
