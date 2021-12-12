package com.advent.of.code.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

class PathFinder {
    List<String> findAllPathsDueToRule(Cave start, BiFunction<Cave, String, Boolean> rule) {
        final List<String> result = new ArrayList<>();
        final String firstPath = start.getName();
        findPaths(start, result, firstPath, rule);
        return result;
    }

    private void findPaths(Cave cave, List<String> allPaths, String path, BiFunction<Cave, String, Boolean> rule) {
        if (cave.isEnd()) {
            allPaths.add(path);
        } else {
            for (Cave adjacent : cave.getAdjacents()) {
                if (rule.apply(adjacent, path)) {
                    findPaths(adjacent, allPaths, path + "," + adjacent.getName(), rule);
                }
            }
        }
    }
}
