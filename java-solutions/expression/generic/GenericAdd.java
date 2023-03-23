package expression.generic;

public class GenericAdd<T> extends GenericAbstractExpression<T> {
    public GenericAdd(GenericGeneral<T> element1, GenericGeneral<T> element2) {
        super(element1, element2);
    }
    @Override
    protected String sign() {
        return "+";
    }
    @Override
    public T count(T a, T b, GenericCalc<T> calculator) {
        return calculator.add(a, b);
    }
}

