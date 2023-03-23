"use strict";

const cnst = a => () => a;

let variable;
<<<<<<< HEAD
variable = varName => (...args) => {
    switch (varName) {
        case "x":
            return args[0];
        case "y":
            return args[1];
        case "z":
            return args[2];
=======
variable = (varName) => (...args) => {
        switch (varName) {
            case "x":
                return args[0];
            case "y":
                return args[1];
            case "z":
                return args[2];
        }
>>>>>>> 053d013e58baa8376d89fc73149a3e271db328e3
    }


<<<<<<< HEAD
let operation;
operation = (operation) => (...op) => (...args) => operation(...op.map(op => op(...args)));

const add = operation((a, b) => a + b);
=======
let binaryOperation;
binaryOperation = (operation, ...opers) => (x, y, z) => operation(opers.map());

const add = binaryOperation((a, b) => a + b);
>>>>>>> 053d013e58baa8376d89fc73149a3e271db328e3

const subtract = operation((a, b) => a - b);

const multiply = operation((a, b) => a * b);

const divide = operation((a, b) => a / b);

const negate = operation(a => -a)

const one = cnst(1);
const two = cnst(2);

<<<<<<< HEAD
const sin = operation(Math.sin);
const cos = operation(Math.cos);
=======
const sin = Math.sin;
const cos = Math.cos;
>>>>>>> 053d013e58baa8376d89fc73149a3e271db328e3
