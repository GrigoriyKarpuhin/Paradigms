package expression.generic;

public class IntegerCalc implements GenericCalc<Integer> {

    @Override
    public Integer add(Integer a, Integer b) {
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
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
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
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        switch (Integer.signum(a) * Integer.signum(b)) {
            case 1 -> {
                if (Integer.signum(a) == 1) {
                    if (Integer.MAX_VALUE / a < b) {
                        throw new ArithmeticException("overflow");
                    }
                } else {
                    if (Integer.MAX_VALUE / a > b) {
                        throw new ArithmeticException("overflow");
                    }
                }
            }
            case -1 -> {
                if (Integer.signum(a) == 1) {
                    if (Integer.MIN_VALUE / a > b) {
                        throw new ArithmeticException("overflow");
                    }
                } else {
                    if (Integer.MIN_VALUE / b > a) {
                        throw new ArithmeticException("overflow");
                    }
                }
            }
        }
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b == 0) {
            throw new ArithmeticException("division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new ArithmeticException("overflow");
        }
        return a / b;
    }

    @Override
    public Integer negate(Integer a) {
        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow");
        }
        return -a;
    }

    @Override
    public Integer parse(String s) throws IllegalArgumentException{
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid integer format: " + s);
        }
    }
}
