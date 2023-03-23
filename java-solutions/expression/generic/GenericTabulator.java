package expression.generic;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    private final Map<String, GenericCalc<?>> operations = Map.of(
            "i", new IntegerCalc(),
            "d", new DoubleCalc(),
            "bi", new BigIntegerCalc(),
            "u", new IntCalc(),
            "f", new FloatCalc(),
            "s", new ShortCalc(),
            "l", new LongCalc()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        GenericCalc<?> calc = operations.get(mode);
        return count(expression, x1, x2, y1, y2, z1, z2, calc);
    }

    private <T> Object[][][] count(String expression, int x1, int x2, int y1, int y2, int z1, int z2, GenericCalc<T> calc) throws Exception {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        GenericExpressionParser<T> parser = new GenericExpressionParser<>(calc);
        GenericGeneral<T> expr = parser.parse(expression);
        for (int x = x1; x < x2 + 1; x++) {
            for (int y = y1; y < y2 + 1; y++) {
                for (int z = z1; z < z2 + 1; z++) {
                    try {
                        result[x - x1][y - y1][z - z1] = expr.evaluate(calc.parse(String.valueOf(x)),
                                calc.parse(String.valueOf(y)),
                                calc.parse(String.valueOf(z)),
                                calc);
                    } catch (Exception e) {
                        result[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }
        return result;
    }
}
