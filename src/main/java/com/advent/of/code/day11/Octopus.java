package com.advent.of.code.day11;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Octopus {
    private Position position;
    private List<Position> adjacents;
    private int lastUpdateStep = 0;
    private int energy;

    Octopus(Position position, int energy, int maxX, int maxY) {
        this.position = position;
        this.energy = energy;
        this.adjacents = initAdjacents(maxX, maxY);
    }

    private List<Position> initAdjacents(int maxX, int maxY) {
        return Stream.of(
                        this.position.left(),
                        this.position.topLeft(),
                        this.position.top(),
                        this.position.topRight(),
                        this.position.right(),
                        this.position.bottomRight(),
                        this.position.bottom(),
                        this.position.bottomLeft()
                )
                .filter(p -> p.x() >= 0 && p.x() < maxX && p.y() >= 0 && p.y() < maxY)
                .collect(Collectors.toList());
    }

    public List<Position> getAdjacents() {
        return adjacents;
    }

    public boolean tryFlash(int updateStep) {
        if (this.lastUpdateStep == updateStep && this.energy == 0) {
            return false;
        } else {
            this.lastUpdateStep = updateStep;
            this.energy = (this.energy + 1) % 10;
            return this.energy == 0;
        }
    }
}
