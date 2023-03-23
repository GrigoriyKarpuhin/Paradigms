package expression.generic;

public class LongCalc implements GenericCalc<Long> {

    public Long add(Long a, Long b) {
        return a + b;
    }

    public Long subtract(Long a, Long b) {
        return a - b;
    }

    public Long multiply(Long a, Long b) {
        return a * b;
    }

    public Long divide(Long a, Long b) {
        return a / b;
    }

    public Long negate(Long a) {
        return -a;
    }

    @Override
    public Long parse(String s) throws IllegalArgumentException{
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid long number: " + s);
        }
    }
}
