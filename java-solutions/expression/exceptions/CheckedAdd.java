package expression.exceptions;

import expression.AbstractExpression;
import expression.General;

public class CheckedAdd extends AbstractExpression {

    public CheckedAdd(General vy, General vx) {
        super(vy, vx);
    }

    @Override
    protected String sign() {
        return "+";
    }

    @Override
    public int count(int a, int b) {
        int res = a + b;
        switch (Integer.signum(a)) {
            case 1 -> {
                if (Integer.MAX_VALUE - a < b) {
                    throw new ArithmeticException("overflow");
                }
            }
            case -1 -> {
                if (Integer.MIN_VALUE - a > b) {
                    throw new ArithmeticException("overflow");
                }
            }
        }
        return res;
    }
}
