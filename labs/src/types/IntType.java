package types;

public class IntType implements Type {
	public IntType() {}

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
	public boolean isFunType() {
		return false;
	}

	@Override
	public boolean isArrayType() {
		return false;
	}

	@Override
	public boolean isDoubleType() {
		return false;
	}

	@Override
	public String toString() {
		return "int";
	}
}
