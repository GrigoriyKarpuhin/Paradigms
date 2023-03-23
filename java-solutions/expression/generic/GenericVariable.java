package expression.generic;

public class GenericVariable<T> implements GenericGeneral<T> {
    private final String variable;

    public GenericVariable(String name) {
        this.variable = name;
    }

    public T evaluate(T number, GenericCalc<T> calculator) {
        return number;
    }

    public T evaluate(T x, T y, T z, GenericCalc<T> calculator) {
        return switch (variable) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalArgumentException("Unknown variable: " + variable);
        };
    }

    public String toString() {
        return variable;
    }
}

