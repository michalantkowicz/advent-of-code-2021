package com.advent.of.code.day19;

record Point(int a, int b, int c) {
    Point sub(Point that) {
        return new Point(this.a - that.a, this.b - that.b, this.c - that.c);
    }

    Point add(Point that) {
        return new Point(this.a + that.a, this.b + that.b, this.c + that.c);
    }
}