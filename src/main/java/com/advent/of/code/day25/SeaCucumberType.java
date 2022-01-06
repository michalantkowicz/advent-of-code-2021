package com.advent.of.code.day25;

import java.util.function.BiFunction;

enum SeaCucumberType {
    EAST((current, max) -> new Point((current.x() + 1 < max.x()) ? current.x() + 1 : 0, current.y())),
    SOUTH((current, max) -> new Point(current.x(), (current.y() + 1 < max.y()) ? current.y() + 1 : 0));

    private BiFunction<Point, Point, Point> calculation;

    SeaCucumberType(BiFunction<Point, Point, Point> calculation) {
        this.calculation = calculation;
    }

    public Point calculateNextPosition(Point currentPosition, Point maxPosition) {
        return calculation.apply(currentPosition, maxPosition);
    }
}
