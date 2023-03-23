package expression.generic;

public class FloatCalc implements GenericCalc<Float>{

    @Override
    public Float add(Float a, Float b) {
        return a + b;
    }

    @Override
    public Float subtract(Float a, Float b) {
        return a - b;
    }

    @Override
    public Float multiply(Float a, Float b) {
        return a * b;
    }

    @Override
    public Float divide(Float a, Float b) {
        return a / b;
    }

    @Override
    public Float negate(Float a) {
        return -a;
    }

    @Override
    public Float parse(String s) throws IllegalArgumentException{
        try {
            return (float) Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid float number: " + s);
        }
    }
}
