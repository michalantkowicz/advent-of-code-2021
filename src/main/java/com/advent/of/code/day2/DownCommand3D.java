package com.advent.of.code.day2;

class DownCommand3D implements Command {
    private int value;

    DownCommand3D(int value) {
        this.value = value;
    }

    @Override
    public Position calculateNewPosition(Position position) {
        return new Position(position.depth(), position.horizontal(), position.aim() + value);
    }
}
