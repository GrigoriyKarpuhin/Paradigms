package expression.exceptions;

import expression.AbstractExpression;
import expression.General;

public class Clear extends AbstractExpression {
    public Clear(General element1, General element2) {
        super(element1, element2);
    }

    @Override
    protected String sign() {
        return "clear";
    }

    @Override
    public int count(int a, int b) {
        return a & ~(1 << b);
    }
}