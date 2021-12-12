package com.advent.of.code.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Cave {
    private String name;
    private List<Cave> adjacents = new ArrayList<>();
    private boolean isEnd;

    Cave(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    List<Cave> getAdjacents() {
        return adjacents;
    }

    boolean canBeVisited(String path) {
        var r = !name.equals(name.toLowerCase()) || !("," + path + ",").contains("," + this.name + ",");
        return r;
    }

    boolean canBeVisitedDueToNewRule(String path) {
        Map<String, Long> x = Arrays.stream(path.split(",")).collect(Collectors.groupingBy(v -> v, Collectors.counting()));
        var r = !name.equals(name.toLowerCase()) || !("," + path + ",").contains("," + this.name + ",") 
                || (!this.getName().equals("start") && !this.name.equals("end") && x.entrySet().stream().filter(e -> e.getKey().equals(e.getKey().toLowerCase())).noneMatch(e -> e.getValue() > 1));
        return r;
    }

    void addAdjacent(Cave adjacent) {
        this.adjacents.add(adjacent);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
