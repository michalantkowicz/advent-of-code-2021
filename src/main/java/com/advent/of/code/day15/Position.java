package com.advent.of.code.day15;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Position(int x, int y) {
    Position left() {
        return new Position(this.x - 1, this.y);
    }

    Position top() {
        return new Position(this.x, this.y + 1);
    }

    Position right() {
        return new Position(this.x + 1, this.y);
    }

    Position bottom() {
        return new Position(this.x, this.y - 1);
    }

    List<Position> getAdjacents(int minX, int maxX, int minY, int maxY) {
        return Stream.of(
                        this.left(),
                        this.top(),
                        this.right(),
                        this.bottom()
                )
                .filter(p -> p.validate(minX, maxX, minY, maxY)).collect(Collectors.toList());
    }

    private boolean validate(int minX, int maxX, int minY, int maxY) {
        return x() >= minX && x() < maxX && y() >= minY && y() < maxY;
    }
}