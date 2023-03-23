package expression.generic;

import java.math.BigInteger;

public class BigIntegerCalc implements GenericCalc<BigInteger>{

    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger parse(String s) throws IllegalArgumentException{
        try {
            return new BigInteger(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid BigInteger number: " + s);
        }
    }
}
