package com.advent.of.code.day14;

record Rule(char left, char right, char result) {
    boolean isValid(char left, char right) {
        return this.left == left && this.right == right;
    }
}
