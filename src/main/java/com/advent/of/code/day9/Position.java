package com.advent.of.code.day9;

record Position(int x, int y) {
    Position left() {
        return new Position(this.x - 1, this.y);
    }

    Position right() {
        return new Position(this.x + 1, this.y);
    }

    Position down() {
        return new Position(this.x, this.y - 1);
    }

    Position up() {
        return new Position(this.x, this.y + 1);
    }
}
