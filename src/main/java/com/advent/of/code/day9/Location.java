package com.advent.of.code.day9;

class Location {
    private final int x;
    private final int y;
    private final int height;
    private int basinSize = 0;

    public Location(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
