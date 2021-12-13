package com.advent.of.code.day13;

record FoldXCommand(int x) implements FoldCommand {
    @Override
    public Point process(Point point) {
        int newX = (point.x() > x) ? point.x() - 2 * (point.x() - x) : point.x();
        return new Point(newX, point.y());
    }
}