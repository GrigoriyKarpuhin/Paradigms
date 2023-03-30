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
        }
    }
    return Operation;
}

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

const mapOp = {
    "+": [Add, 2],
    "-": [Subtract, 2],
    "*": [Multiply, 2],
    "/": [Divide, 2],
    "negate": [Negate, 1],
    "sinh": [Sinh, 1],
    "cosh": [Cosh, 1],
    "min3": [Min3, 3],
    "max5": [Max5, 5],
    "exp": [Exp, 1],
    "ln": [Ln, 1]
}

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
