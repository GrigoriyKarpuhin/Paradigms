"use strict";

let negate;
negate = a => (x, y, z) => -a(x, y, z);

let cnst;
cnst = a => () => a;

let variable;
variable = function (varName) {
    return function (...args) {
        switch (varName) {
            case "x":
                return args[0];
            case "y":
                return args[1];
            case "z":
                return args[2];
        }
    }
}

let binaryOperation;
binaryOperation = (operation) => (a, b) => (x, y, z) => operation(a(x, y, z), b(x, y, z));

let add;
add = binaryOperation((a, b) => a + b);

let subtract;
subtract = binaryOperation((a, b) => a - b);

let multiply;
multiply = binaryOperation((a, b) => a * b);

let divide;
divide = binaryOperation((a, b) => a / b);

const one = cnst(1);
const two = cnst(2);

const sin = a => (x, y, z) => Math.sin(a(x, y, z));
const cos = b => (x, y, z) => Math.cos(b(x, y, z));