package com.advent.of.code.day2;

class ForwardCommand2D implements Command {
    private int value;

    ForwardCommand2D(int value) {
        this.value = value;
    }

    @Override
    public Position calculateNewPosition(Position position) {
        return new Position(position.depth(), position.horizontal() + value, position.aim());
    }
}
