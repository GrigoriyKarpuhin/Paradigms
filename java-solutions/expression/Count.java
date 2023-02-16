package expression;

public class Count implements General {

    private final General element;

    public Count(General element) {
        this.element = element;
    }

    public int count(int a) {
        return Integer.bitCount(a);
    }

    @Override
    public int evaluate(int x) {
        return count(element.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return count(element.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("count").append("(").append(element.toString()).append(")");
        return String.valueOf(builder);
    }

    @Override
    public boolean equals(Object object) {
        if (object != null) {
            return ((object instanceof Count expr) &&
                    expr.element.equals(this.element));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 29 * element.hashCode();
    }
}
