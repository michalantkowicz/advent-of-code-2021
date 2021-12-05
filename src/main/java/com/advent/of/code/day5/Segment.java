package com.advent.of.code.day5;

import static java.lang.Integer.max;
import static java.lang.Math.min;

record Segment(int x1, int y1, int x2, int y2) {
    boolean doesContainPoint(int x, int y) {
        if (x < min(x1, x2) || x > max(x1, x2) || y < min(y1, y2) || y > max(y1, y2)) {
            return false;
        } else if (determinant(x1, y1, x2, y2, x, y) != 0) {
            return false;
        } else {
            return true;
        }
    }

    int getMinX() {
        return min(x1, x2);
    }

    int getMaxX() {
        return max(x1, x2);
    }

    int getMinY() {
        return min(y1, y2);
    }

    int getMaxY() {
        return max(y1, y2);
    }

    private int determinant(int x1, int y1, int x2, int y2, int x3, int y3) {
        return ((x1 * y2) + (x2 * y3) + (x3 * y1)) - ((x2 * y1) + (x1 * y3) + (x3 * y2));
    }
}
