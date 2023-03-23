package expression.generic;

public class ShortCalc implements GenericCalc<Short>{

    @Override
    public Short add(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    public Short subtract(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    public Short multiply(Short a, Short b) {
        return (short) (a * b);
    }

    @Override
    public Short divide(Short a, Short b) {
        return (short) (a / b);
    }

    @Override
    public Short negate(Short a) {
        return (short) -a;
    }

    @Override
    public Short parse(String s) throws IllegalArgumentException{
        try {
            return (short) Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid short number: " + s);
        }
    }
}
