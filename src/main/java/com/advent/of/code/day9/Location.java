package com.advent.of.code.day9;

class Location {
    private final Position position;
    private final int height;
    private int basinSize = 0;

    public Location(Position position, int height) {
        this.position = position;
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public int getHeight() {
        return height;
    }

    public int getBasinSize() {
        return basinSize;
    }

    void incrementBasinSize() {
        this.basinSize++;
    }
}
