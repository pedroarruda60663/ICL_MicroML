package types;

public interface Type {
    boolean isIntType();
    boolean isBoolType();
    boolean isRefType();
    boolean isUnitType();
    boolean isFunType();
    boolean isArrayType();
}
