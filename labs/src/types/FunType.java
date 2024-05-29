package types;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FunType implements Type {
    public Type resultType;
    public List<Type> arguments;

    public FunType(List<Type> arguments, Type t) {
        this.resultType = t;
        this. arguments = arguments;
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
    public boolean isFunType() { return true; }

    @Override
    public String toString() {
        return "function";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FunType other = (FunType) obj;
        return Objects.equals(resultType, other.resultType) &&
                Objects.equals(arguments, other.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultType, arguments);
    }
}
