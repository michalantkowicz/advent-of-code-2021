package com.advent.of.code.day18;

record Node(int value, Node parent, Node left, Node right) {
    boolean isLeaf() {
        return left == null && right == null;
    }

    int magnitude() {
        if (isLeaf()) {
            return value;
        } else {
            return (3 * left.magnitude()) + (2 * right.magnitude());
        }
    }
}