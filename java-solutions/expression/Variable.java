package expression;

public class Variable implements General {

    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(int varInt) {
        return varInt;
    }
    public int evaluate(int x, int y, int z) {
        return switch (variable) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalArgumentException("Unknown variable: " + variable);
        };
    }

    public String toString() {
        return variable;
    }
    public boolean equals(Object object) {
        if (object != null) {
            return ((object instanceof Variable) &&
                    ((Variable) object).variable.equals(this.variable));
        } else {
            return false;
        }
    }
    public int hashCode() {
        return variable.hashCode();
    }
}
