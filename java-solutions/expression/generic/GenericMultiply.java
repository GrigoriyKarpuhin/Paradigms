package expression.generic;

public class GenericMultiply<T> extends GenericAbstractExpression<T> {
    public GenericMultiply(GenericGeneral<T> element1, GenericGeneral<T> element2) {
        super(element1, element2);
    }

    @Override
    protected String sign() {
        return "*";
    }

    @Override
    public T count(T a, T b, GenericCalc<T> calculator) {
        return calculator.multiply(a, b);
    }
}

