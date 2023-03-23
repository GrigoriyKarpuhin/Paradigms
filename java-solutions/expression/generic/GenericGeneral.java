package expression.generic;

public interface GenericGeneral<T> {
    T evaluate(T x, T y, T z, GenericCalc<T> calculator);
    T evaluate(T x, GenericCalc<T> calculator);
}
