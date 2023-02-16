package expression.exceptions;

import expression.General;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(General vy) {
        super(vy);
    }

    @Override
    public int count(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow");
        }
        return -a;
    }
}
