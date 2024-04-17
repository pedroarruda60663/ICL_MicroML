package types;

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
    public String toString() {
        return "ref_"+refOf.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
