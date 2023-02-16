package expression;

public class Const implements General {

    private final int constant;

    public Const(int constant) {
        this.constant = constant;
    }

    public int evaluate(int constInt) {
        return constant;
    }

    public int evaluate(int x, int y, int z) {
        return constant;
    }

    public String toString() {
        return String.valueOf(constant);
    }

    public boolean equals(Object object) {
        if (object != null) {
            return ((object instanceof Const) &&
                    ((Const) object).constant == (this.constant));
        } else {
            return false;
        }
    }

    public int hashCode() {
        return constant;
    }
}

