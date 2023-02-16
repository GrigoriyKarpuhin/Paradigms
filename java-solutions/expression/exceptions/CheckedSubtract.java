package expression.exceptions;

import expression.AbstractExpression;
import expression.General;

public class CheckedSubtract extends AbstractExpression {

    public CheckedSubtract(General vy, General vx) {
        super(vy, vx);
    }

    @Override
    protected String sign() {
        return "-";
    }

    @Override
    public int count(int a, int b) {
        int res = a - b;
        if (b == Integer.MIN_VALUE) {
            if (a > -1) {
                throw new ArithmeticException("overflow");
            }
        } else {
            switch (Integer.signum(a)) {
                case 1 -> {
                    if (Integer.MAX_VALUE - a < -b) {
                        throw new ArithmeticException("overflow");
                    }
                }
                case -1 -> {
                    if (Integer.MIN_VALUE - a > -b) {
                        throw new ArithmeticException("overflow");
                    }
                }
            }
        }
        return res;
    }
}
