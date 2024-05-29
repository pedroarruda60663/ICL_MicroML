package types;

public class UnitType implements Type {
    private static final UnitType instance = new UnitType();

    private UnitType() {}

    public static UnitType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "unit";
    }

    @Override
    public boolean isUnitType() {
        return true;
    }

    @Override
    public boolean isFunType() {
        return false;
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
}
