package expression;

public abstract class AbstractExpression implements General {

    private final General element1;
    private final General element2;

    protected abstract String sign();

    public AbstractExpression(General element1, General element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public abstract int count(int a, int b);

    public int evaluate(int number) {
        return count(element1.evaluate(number), element2.evaluate(number));
    }
    public int evaluate(int x, int y, int z) {
        return count(element1.evaluate(x, y, z), element2.evaluate(x, y , z));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("(").append(element1.toString()).append(" ").append(sign()).append(" ").append(element2.toString()).append(")");
        return String.valueOf(builder);
    }

    public boolean equals(Object object) {
        if (object != null) {
            return ((object instanceof AbstractExpression expr) &&
                    expr.element1.equals(this.element1) &&
                    expr.element2.equals(this.element2) &&
                    expr.sign().equals(sign()));
        } else {
            return false;
        }
    }
    public int hashCode() {
        return (29 * element1.hashCode() + element2.hashCode()) * 29 + sign().hashCode();
    }
}