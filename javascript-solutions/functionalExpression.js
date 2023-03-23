"use strict";

const cnst = a => () => a;

let variable;
variable = varName => (...args) => {
    switch (varName) {
        case "x":
            return args[0];
        case "y":
            return args[1];
        case "z":
            return args[2];
    }
}

let operation;
operation = (operation) => (...op) => (...args) => operation(...op.map(op => op(...args)));

const add = operation((a, b) => a + b);

const subtract = operation((a, b) => a - b);

const multiply = operation((a, b) => a * b);

const divide = operation((a, b) => a / b);

const negate = operation(a => -a)

const one = cnst(1);
const two = cnst(2);

const sin = operation(Math.sin);
const cos = operation(Math.cos);
