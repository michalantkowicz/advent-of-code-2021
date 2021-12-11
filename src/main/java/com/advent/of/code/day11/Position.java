package com.advent.of.code.day11;

record Position(int x, int y) {
    Position left() {
        return new Position(this.x - 1, this.y);
    }

    Position topLeft() {
        return new Position(this.x - 1, this.y + 1);
    }

    Position top() {
        return new Position(this.x, this.y + 1);
    }

    Position topRight() {
        return new Position(this.x + 1, this.y + 1);
    }

    Position right() {
        return new Position(this.x + 1, this.y);
    }

    Position bottomRight() {
        return new Position(this.x + 1, this.y - 1);
    }

    Position bottom() {
        return new Position(this.x, this.y - 1);
    }

    Position bottomLeft() {
        return new Position(this.x - 1, this.y - 1);
    }
}
