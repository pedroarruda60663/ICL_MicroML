package types;

public class ArrayType implements Type {
    private Type elementType;

    public ArrayType(Type elementType) {
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
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
        return true;
    }

    @Override
    public boolean isDoubleType() {
        return false;
    }

    @Override
    public String toString() {
        return "array_" + elementType.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArrayType other = (ArrayType) obj;
        return elementType.equals(other.getElementType());
    }
}
