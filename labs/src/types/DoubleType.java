package types;

public class DoubleType implements Type {
	public DoubleType() {}

	private static DoubleType instance = new DoubleType();

	public static DoubleType getInstance() {
		return instance;
	}

	@Override
	public boolean isIntType() {
		return false;
	}

	@Override
	public boolean isBoolType() {
		return false;
	}

	@Override
	public boolean isRefType() {
		return false;
	}

	@Override
	public boolean isUnitType() {
		return false;
	}

	@Override
	public boolean isFunType() {
		return false;
	}

	@Override
	public boolean isArrayType() {
		return false;
	}

	@Override
	public boolean isDoubleType() {
		return true;
	}

	@Override
	public String toString() {
		return "double";
	}
}
