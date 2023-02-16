package expression.exceptions;

import expression.AbstractExpression;
import expression.General;

public class CheckedDivide extends AbstractExpression {

    public CheckedDivide(General element1, General element2) {
        super(element1, element2);
    }

    @Override
    protected String sign() {
        return "/";
    }

    @Override
    public int count(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new ArithmeticException("overflow");
        }
        return a / b;
    }
}
