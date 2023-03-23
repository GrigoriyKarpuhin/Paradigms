package expression.generic;

import expression.parser.*;

public class GenericExpressionParser<T> extends BaseParser {
    private final GenericCalc<T> calc;

    public GenericExpressionParser(GenericCalc<T> calc) {
        this.calc = calc;
    }

    public GenericGeneral<T> parse(String expression) {
        sourceTake(new StringSource(expression));
        return parseExpr();
    }

    private GenericGeneral<T> parseExpr() {
        GenericGeneral<T> result = parseTerm();
        while (true) {
            skipWhitespace();
            if (take('+')) {
                result = new GenericAdd<>(result, parseTerm());
            } else if (take('-')) {
                result = new GenericSubtract<>(result, parseTerm());
            } else {
                return result;
            }
        }
    }

    private GenericGeneral<T> parseTerm() {
        GenericGeneral<T> result = parsePrim();
        while (true) {
            skipWhitespace();
            if (take('*')) {
                result = new GenericMultiply<>(result, parsePrim());
            } else if (take('/')) {
                result = new GenericDivide<>(result, parsePrim());
            } else {
                return result;
            }
        }
    }

    private GenericGeneral<T> parsePrim() {
        skipWhitespace();
        switch (ch) {
            case '-':
                take();
                if (between('0', '9')) {
                    return parseConst(true);
                } else {
                    return new GenericUnaryMinus<>(parsePrim());
                }
            case '(':
                take();
                GenericGeneral<T> result = parseExpr();
                expect(')');
                return result;
            default:
                if (between('0', '9')) {
                    return parseConst(false);
                } else if (test('x') || test('y') || test('z')) {
                    return new GenericVariable<>(String.valueOf(take()));
                } else {
                    throw error("Unexpected symbol: " + '"' + ch + '"');
                }
        }
    }

    private GenericGeneral<T> parseConst(boolean isNegative) {
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append('-');
        }
        while (between('0', '9')) {
            sb.append(ch);
            take();
        }
        return new GenericConst<>(calc.parse(sb.toString()));
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

    private boolean validChecker(String expression) {
        int balance = 0;
        boolean flag = false;
        boolean hasTaken;
        sourceTake(new StringSource(expression));
        while (ch != '\0') {
            hasTaken = false;
            if (flag && between('0', '9')) {
                return false;
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ')' || ch == 's' || ch == 'c') {
                flag = false;
            }
            if (ch == '-') {
                take();
                if (between('0', '9')) {
                    flag = true;
                    while (between('0', '9')) {
                        take();
                        hasTaken = true;
                    }
                }
            }
            if (between('0', '9')) {
                flag = true;
                while (between('0', '9')) {
                    take();
                    hasTaken = true;
                }
            }
            if (ch == 'c') {
                take();
                if (test('o')) {
                    take();
                    expect("unt");
                    hasTaken = true;
                    if (!test('(') && !Character.isWhitespace(ch)) {
                        return false;
                    }
                } else if (test('l')) {
                    take();
                    expect("ear");
                    hasTaken = true;
                    if (!Character.isWhitespace(ch) && ch != '-' && ch != '(') {
                        return false;
                    }
                }
            }
            if (ch == 's') {
                take();
                expect("et");
                hasTaken = true;
                if (!Character.isWhitespace(ch) && ch != '-' && ch != '(') {
                    return false;
                }
            }
            if (hasTaken) {
                continue;
            }
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
            }
            if (!between('0', '9')
                    && ch != '(' && ch != ')'
                    && ch != '+' && ch != '-' && ch != '*' && ch != '/'
                    && ch != 'x' && ch != 'y' && ch != 'z'
                    && ch != 'c' && ch != 'o' && ch != 'u' && ch != 'n' && ch != 't'
                    && ch != 's' && ch != 'e'
                    && ch != 'l' && ch != 'a' && ch != 'r'
                    && ch != '\0'
                    && !Character.isWhitespace(ch)) {
                return false;
            }
            take();
        }
        return balance == 0;
    }
}