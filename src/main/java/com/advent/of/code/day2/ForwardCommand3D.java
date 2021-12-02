package com.advent.of.code.day2;

class ForwardCommand3D implements Command {
    private int value;

    ForwardCommand3D(int value) {
        this.value = value;
    }

    @Override
    public Position calculateNewPosition(Position position) {
        return new Position(
                position.depth() + (position.aim() * value),
                position.horizontal() + value,
                position.aim()
        );
    }
}
