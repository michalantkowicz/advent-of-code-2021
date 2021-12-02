package com.advent.of.code.day2;

class UpCommand2D implements Command {
    private int value;

    UpCommand2D(int value) {
        this.value = value;
    }

    @Override
    public Position calculateNewPosition(Position position) {
        return new Position(position.depth() - value, position.horizontal(), position.aim());
    }
}
