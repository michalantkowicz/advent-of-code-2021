package com.advent.of.code.day19;

record Vector(int x, int y, int z) {
    public static Vector of(Point a, Point b) {
        return new Vector(b.a() - a.a(), b.b() - a.b(), b.c() - a.c());
    }
}