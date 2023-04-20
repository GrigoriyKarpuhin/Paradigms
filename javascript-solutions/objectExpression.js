"use strict"

const Const = function (value) {
    this.value = value;
}

Const.prototype = {
    evaluate: function () {
        return this.value;
    },
    toString: function () {
        return this.value.toString();
    },
    prefix: function () {
        return this.value.toString();
    }
}

const Variable = function (varName) {
    this.name = varName;
}

Variable.prototype = {
    evaluate: function (...args) {
        switch (this.name) {
            case "x":
                return args[0];
            case "y":
                return args[1];
            case "z":
                return args[2];
        }
    },
    toString: function () {
        return this.name;
    },
    prefix: function () {
        return this.name;
    }
}

const OperationCreate = function (operation, sign) {
    const Operation = function (...op) {
        this.op = op;
    }

    Operation.prototype = {
        evaluate: function (...args) {
            return operation(...this.op.map(op => op.evaluate(...args)));
        },
        toString: function () {
            return this.op.join(" ") + " " + sign;
        },
        prefix: function () {
            return '(' + sign + ' ' + this.op.map(op => op.prefix()).join(" ") + ')'
        }
    }
    mapOp[sign] = [Operation, operation.length];
    return Operation;
}

const mapOp = {};
const Add = OperationCreate((a, b) => a + b, "+");
const Subtract = OperationCreate((a, b) => a - b, "-");
const Multiply = OperationCreate((a, b) => a * b, "*");
const Divide = OperationCreate((a, b) => a / b, "/");
const Negate = OperationCreate(a => -a, "negate");
const Sinh = OperationCreate(a => Math.sinh(a), "sinh");
const Cosh = OperationCreate(a => Math.cosh(a), "cosh");
const Min3 = OperationCreate((...op) => Math.min(...op), "min3");
const Max5 = OperationCreate((...op) => Math.max(...op), "max5");
const Exp = OperationCreate(a => Math.exp(a), "exp");
const Ln = OperationCreate(a => Math.log(a), "ln");
const Sum = OperationCreate((...args) => args.reduce((a, b) => a + b, 0), "sum");
const Avg = OperationCreate((...args) => {
    const sum = args.reduce((a, b) => a + b, 0);
    return sum / args.length;
}, "avg");

const mapVar = {
    "x": new Variable("x"),
    "y": new Variable("y"),
    "z": new Variable("z")
}

const parse = function (expression) {
    const tokens = expression.split(" ");
    const stack = [];
    for (let token of tokens) {
        if (token === "") continue;
        if (token in mapVar) {
            stack.push(mapVar[token]);
        } else if (token in mapOp) {
            const [operation, count] = mapOp[token];
            const elements = [];
            for (let i = 0; i < count; i++) {
                elements.push(stack.pop());
            }
            stack.push(new operation(...elements.reverse()));
        } else {
            stack.push(new Const(parseInt(token)));
        }
    }
    return stack.pop();
}

function InvalidExpressionError(message) {
    this.message = message;
}

InvalidExpressionError.prototype = Object.create(Error.prototype);
InvalidExpressionError.prototype.name = "InvalidExpression";
InvalidExpressionError.prototype.constructor = InvalidExpressionError;

const validChecker = function (expressionPref) {
    try {
        const tokens = expressionPref.replace(/\)/g, " ) ").replace(/\(/g, " ( ").trim().split(" ");
        if (expressionPref.replace(/[(, )]/gi, " ").trim().length === 0) {
            throw new InvalidExpressionError("Empty expression");
        }
        let balance = 0;
        let bracketFlag = false;
        for (let token of tokens) {
            if (token === "") continue;
            if (bracketFlag === true) {
                if (token in mapOp) {
                    bracketFlag = false;
                } else {
                    throw new InvalidExpressionError("extra brackets");
                }
            }
            if (token === '(') {
                balance++;
                bracketFlag = true;
            }
            if (token === ')') balance--;
            if (balance < 0) {
                throw new InvalidExpressionError("Wrong bracket sequence");
            }
        }
        if (balance !== 0) {
            throw new InvalidExpressionError("Wrong bracket sequence");
        }
    } catch (e) {
        throw(e);
    }
}
const parsePrefix = function (expressionPref) {
    try {
        validChecker(expressionPref);
        const tokens = expressionPref.replace(/\)/g, " ) ").replace(/\(/gi, " ( ").trim().split(" ").reverse();
        const stack = [];
        let operFlag = false;
        for (let token of tokens) {
            if (token === "") continue;
            if (token in mapVar) {
                if (operFlag === true) {
                    throw new InvalidExpressionError("extra operation");
                }
                stack.push(mapVar[token]);
            } else if (token in mapOp) {
                if (operFlag === true) {
                    throw new InvalidExpressionError("extra operation");
                }
                operFlag = true;
                const [operation, count] = mapOp[token];
                const elements = [];
                if (count !== 0) {
                    if (stack.length < count + 1) {
                        throw new InvalidExpressionError("extra operation");
                    }
                    let temp = 0;
                    while (stack[stack.length - 1] !== ')' && stack.length > 0) {
                        elements.push(stack.pop());
                        temp++;
                    }
                    if (temp !== count) {
                        throw new InvalidExpressionError("extra operation");
                    }
                    stack.pop();
                    stack.push(new operation(...elements));
                } else {
                    while (stack[stack.length - 1] !== ')') {
                        elements.push(stack.pop());
                    }
                    stack.pop();
                    stack.push(new operation(...elements));
                }
            } else if (!isNaN(+token)) {
                if (operFlag === true) {
                    throw new InvalidExpressionError("extra operation");
                }
                stack.push(new Const(parseInt(token)));
            } else if (token === ')') {
                if (operFlag === true) {
                    throw new InvalidExpressionError("extra operation");
                }
                stack.push(token);
            } else if (token === '(') {
                operFlag = false;
            } else {
                throw new InvalidExpressionError("invalid symbol " + token + " in " + expressionPref);
            }
        }
        if (stack.length > 1) {
            throw new InvalidExpressionError("extra operands");
        }
        return stack.pop();
    } catch (e) {
        throw(e);
    }
}