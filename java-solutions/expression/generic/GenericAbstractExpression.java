package expression.generic;

public abstract class GenericAbstractExpression<T> implements GenericGeneral<T> {
    private final GenericGeneral<T> element1;
    private final GenericGeneral<T> element2;

    protected abstract String sign();

    public GenericAbstractExpression(GenericGeneral<T> element1, GenericGeneral<T> element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public abstract T count(T a, T b, GenericCalc<T> calculator);

    public T evaluate(T number, GenericCalc<T> calculator) {
        return count(element1.evaluate(number, calculator), element2.evaluate(number, calculator), calculator);
    }
    public T evaluate(T x, T y, T z, GenericCalc<T> calculator) {
        return count(element1.evaluate(x, y, z, calculator), element2.evaluate(x, y , z, calculator), calculator);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("(").append(element1.toString()).append(" ").append(sign()).append(" ").append(element2.toString()).append(")");
        return String.valueOf(builder);
    }
}
