package types;

import values.RefValue;

public class RefType implements Type {
    private Type refOf;

    public RefType(Type t) { this.refOf = t; }

    public Type getInner() { return refOf; }

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
        return "ref_"+refOf.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RefType other = (RefType) obj;
        return refOf.equals(other.getInner());
    }
}
