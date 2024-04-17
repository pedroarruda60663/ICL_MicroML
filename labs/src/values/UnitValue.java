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
	public boolean isIntValue() {
		return false;
	}

	@Override
	public boolean isBoolValue() {
		return false;
	}

	@Override
	public boolean isRefValue() {
		return false;
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
}
