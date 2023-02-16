package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser implements TripleParser {

    public TripleExpression parse(String expression) {
        sourceTake(new StringSource(expression));
        return parseExpr();
    }

    private TripleExpression parseExpr() {
        TripleExpression result = parseTerm();
        while (true) {
            skipWhitespace();
            if (take('+')) {
                result = new Add((General) result, (General) parseTerm());
            } else if (take('-')) {
                result = new Subtract((General) result, (General) parseTerm());
            } else {
                return result;
            }
        }
    }

    private TripleExpression parseTerm() {
        TripleExpression result = parsePrim();
        while (true) {
            skipWhitespace();
            if (take('*')) {
                result = new Multiply((General) result, (General) parsePrim());
            } else if (take('/')) {
                result = new Divide((General) result, (General) parsePrim());
            } else {
                return result;
            }
        }
    }

    private TripleExpression parsePrim() {
        skipWhitespace();
        switch (ch) {
            case '-':
                take();
                if (between('0', '9')) {
                    return parseConst(true);
                } else {
                    return new UnaryMinus((General) parsePrim());
                }
            case '(':
                take();
                TripleExpression result = parseExpr();
                expect(')');
                return result;
            case 'c':
                take();
                expect("ount");
                if (!test('(')) {
                    return new Count((General) parsePrim());
                } else {
                    expect('(');
                    result = parseExpr();
                    expect(')');
                    return new Count((General) result);
                }
            default:
                if (between('0', '9')) {
                    return parseConst(false);
                } else if (test('x') || test('y') || test('z')) {
                    return new Variable(String.valueOf(take()));
                } else {
                    throw error("Unexpected symbol");
                }
        }
    }

    private TripleExpression parseConst(boolean subzero) {
        StringBuilder number = new StringBuilder();
        if (subzero) {
            number.append('-');
        }
        while (between('0', '9')) {
            number.append(take());
        }
        return new Const(Integer.parseInt(number.toString()));
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
