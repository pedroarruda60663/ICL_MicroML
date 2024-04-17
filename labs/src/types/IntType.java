package types;

public class IntType implements Type {
	private IntType() {}

	private static IntType instance = new IntType();

	public static IntType getInstance() {
		return instance;
	}

	@Override
	public boolean isIntType() {
		return true;
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
	public String toString() {
		return "int";
	}
}
