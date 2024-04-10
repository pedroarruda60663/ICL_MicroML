package types;

public class BoolType implements Type {
    // Private constructor to prevent instantiation
    private BoolType() {}

    // Singleton instance
    private static BoolType instance = new BoolType();

    // Static method to get the instance
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
    public String toString() {
        return "bool";
    }
}
