package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;
import expression.parser.TripleParser;

public class ExpressionParser extends BaseParser implements TripleParser {

    public TripleExpression parse(String expression) {
        if (validChecker(expression)) {
            sourceTake(new StringSource(expression));
            return parseExpr();
        } else {
            try {
                throw new InvalidInputException("Invalid expression: " + expression);
            } catch (InvalidInputException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private TripleExpression parseExpr() {
        TripleExpression result = parseTerm();
        while (true) {
            skipWhitespace();
            switch (ch) {
                case '+' -> {
                    take();
                    result = new CheckedAdd((General) result, (General) parseTerm());
                }
                case '-' -> {
                    take();
                    result = new CheckedSubtract((General) result, (General) parseTerm());
                }
                case 's' -> {
                    take();
                    expect("et");
                    result = new Set((General) result, (General) parseTerm());
                }
                case 'c' -> {
                    take();
                    if (test('l')) {
                        take();
                        expect("ear");
                        result = new Clear((General) result, (General) parseTerm());
                    }
                }
                default -> {
                    return result;
                }
            }
        }
    }

    private TripleExpression parseTerm() {
        TripleExpression result = parsePrim();
        while (true) {
            skipWhitespace();
            switch (ch) {
                case '*' -> {
                    take();
                    result = new CheckedMultiply((General) result, (General) parsePrim());
                }
                case '/' -> {
                    take();
                    result = new CheckedDivide((General) result, (General) parsePrim());
                }
                default -> {
                    return result;
                }
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
                    return new CheckedNegate((General) parsePrim());
                }
            case '(':
                take();
                TripleExpression result = parseExpr();
                expect(')');
                return result;
            case 'c':
                take();
                if (test('o')) {
                    take();
                    expect("unt");
                    if (!test('(')) {
                        return new Count((General) parsePrim());
                    } else {
                        expect('(');
                        result = parseExpr();
                        expect(')');
                        return new Count((General) result);
                    }
                }
            default:
                if (between('0', '9')) {
                    return parseConst(false);
                } else if (test('x') || test('y') || test('z')) {
                    return new Variable(String.valueOf(take()));
                } else {
                    throw error("Unexpected symbol: " + '"' + ch + '"');
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
