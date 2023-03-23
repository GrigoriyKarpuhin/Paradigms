package expression.generic;

public class DoubleCalc implements GenericCalc<Double> {


    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double parse(String s) throws IllegalArgumentException {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid double number: " + s);
        }
    }
}
