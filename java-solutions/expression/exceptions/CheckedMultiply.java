package expression.exceptions;

import expression.AbstractExpression;
import expression.General;

public class CheckedMultiply extends AbstractExpression {
    public CheckedMultiply(General vy, General vx) {
        super(vy, vx);
    }

    @Override
    protected String sign() {
        return "*";
    }

    @Override
    public int count(int a, int b) {
        int res = a * b;
        switch (Integer.signum(a) * Integer.signum(b)) {
            case 1 -> {
                if (Integer.signum(a) == 1) {
                    if (Integer.MAX_VALUE / a < b) {
                        throw new ArithmeticException("overflow");
                    }
                } else {
                    if (Integer.MAX_VALUE / a > b) {
                        throw new ArithmeticException("overflow");
                    }
                }
            }
            case -1 -> {
                if (Integer.signum(a) == 1) {
                    if (Integer.MIN_VALUE / a > b) {
                        throw new ArithmeticException("overflow");
                    }
                } else {
                    if (Integer.MIN_VALUE / b > a) {
                        throw new ArithmeticException("overflow");
                    }
                }
            }
        }
        return res;
    }
}
