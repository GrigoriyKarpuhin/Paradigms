package expression;

public class Divide extends AbstractExpression {

    public Divide(General element1, General element2) {
        super(element1, element2);
    }
    @Override
    protected String sign() {
        return "/";
    }
    @Override
    public int count(int a, int b) {
        return a / b;
    }
}