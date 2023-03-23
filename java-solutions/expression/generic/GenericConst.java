package expression.generic;

public class GenericConst<T> implements GenericGeneral<T> {
    private final T constant;

    public GenericConst(T constant) {
        this.constant = constant;
    }

    public T evaluate(T number, GenericCalc<T> calculator) {
        return constant;
    }
    public T evaluate(T x, T y, T z, GenericCalc<T> calculator) {
        return constant;
    }

    public String toString() {
        return String.valueOf(constant);
    }
}
