package expression;

public class Add extends AbstractExpression {

    public Add(General element1, General element2) {
        super(element1, element2);
    }
    @Override
    protected String sign() {
        return "+";
    }
    @Override
    public int count(int a, int b) {
        return a + b;
    }
}