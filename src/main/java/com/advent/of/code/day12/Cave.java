package com.advent.of.code.day12;

import java.util.ArrayList;
import java.util.List;

class Cave {
    private final String name;
    private final List<Cave> adjacents = new ArrayList<>();

    Cave(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    List<Cave> getAdjacents() {
        return adjacents;
    }

    void addAdjacent(Cave adjacent) {
        this.adjacents.add(adjacent);
    }

    public boolean isEnd() {
        return "end".equals(name);
    }
}
