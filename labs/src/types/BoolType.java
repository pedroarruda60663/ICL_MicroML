package types;

public class BoolType implements Type {
    public BoolType() {}
    private static BoolType instance = new BoolType();

    public static BoolType getInstance() {
        return instance;
    }

    @Override
    public boolean isIntType() {
        return false;
    }

    @Override
    public boolean isBoolType() {
        return true;
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
        return "bool";
    }
}
