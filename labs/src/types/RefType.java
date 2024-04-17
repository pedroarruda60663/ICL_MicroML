package types;

public class RefType implements Type {
    private RefType() {}

    private static RefType instance = new RefType();

    public static RefType getInstance() {
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
        return true;
    }

    @Override
    public String toString() {
        return "ref";
    }
}
