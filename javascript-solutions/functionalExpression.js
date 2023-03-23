"use strict";

let negate;
negate = a => (x, y, z) => -a(x, y, z);

let cnst;
cnst = a => () => a;

let variable;
variable = (varName) => (...args) => {
        switch (varName) {
            case "x":
                return args[0];
            case "y":
                return args[1];
            case "z":
                return args[2];
        }
    }


let binaryOperation;
binaryOperation = (operation, ...opers) => (x, y, z) => operation(opers.map());

const add = binaryOperation((a, b) => a + b);

let subtract;
subtract = binaryOperation((a, b) => a - b);

let multiply;
multiply = binaryOperation((a, b) => a * b);

let divide;
divide = binaryOperation((a, b) => a / b);

const one = cnst(1);
const two = cnst(2);

const sin = Math.sin;
const cos = Math.cos;