package com.advent.of.code.day2;

class UpCommand3D implements Command {
    private int value;

    UpCommand3D(int value) {
        this.value = value;
    }

    @Override
    public Position calculateNewPosition(Position position) {
        return new Position(position.depth(), position.horizontal(), position.aim() - value);
    }
}
