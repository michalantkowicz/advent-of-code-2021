package com.advent.of.code.day13;

record FoldYCommand(int y) implements FoldCommand {
    @Override
    public Point process(Point point) {
        int newY = (point.y() > y) ? point.y() - 2 * (point.y() - y) : point.y();
        return new Point(point.x(), newY);
    }
}