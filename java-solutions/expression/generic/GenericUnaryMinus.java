package expression.generic;

public class GenericUnaryMinus<T> implements GenericGeneral<T> {

    private final GenericGeneral<T> element;

    public GenericUnaryMinus(GenericGeneral<T> element) {
        this.element = element;
    }

    public T count(T a, GenericCalc<T> calculator) {
        return calculator.negate(a);
    }

    public T evaluate(T number, GenericCalc<T> calculator) {
        return count(element.evaluate(number, calculator), calculator);
    }

    public T evaluate(T x, T y, T z, GenericCalc<T> calculator) {
        return count(element.evaluate(x, y, z, calculator), calculator);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("-").append("(").append(element.toString()).append(")");
        return String.valueOf(builder);
    }
}
