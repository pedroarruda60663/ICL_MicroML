package values;

public class UnitValue implements Value {
	private static final UnitValue instance = new UnitValue();

	private UnitValue() {}

	public static UnitValue getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "unit";
	}

	@Override
	public IntValue asIntValue() {
		return null;
	}

	@Override
	public BoolValue asBoolValue() {
		return null;
	}

	@Override
	public RefValue asRefValue() {
		return null;
	}

	@Override
	public ClosureValue asClosureValue() {
		return null;
	}

	@Override
	public ArrayValues asArrayValue() {throw new IllegalStateException("Not a ArrayValues");}

	@Override
	public DoubleValue asDoubleValue() {
		throw new IllegalStateException("Not a DoubleValue");
	}
}
