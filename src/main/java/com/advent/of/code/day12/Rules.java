package com.advent.of.code.day12;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Rules {
    static boolean smallCaveVisitedOnce(Cave cave, String path) {
        return nameIsUppercase(cave) || pathDoesNotContainsCave(cave, path);
    }

    static boolean oneOfSmallCavesMayBeVisitedTwice(Cave cave, String path) {
        return nameIsUppercase(cave) || pathDoesNotContainsCave(cave, path)
                || (caveIsNotStartOrEnd(cave) && anySmallCaveVisitedTwiceYet(path));
    }

    private static boolean nameIsUppercase(Cave cave) {
        return cave.getName().equals(cave.getName().toUpperCase());
    }

    private static boolean pathDoesNotContainsCave(Cave cave, String path) {
        return stream(path.split(",")).noneMatch(caveName -> caveName.equals(cave.getName()));
    }

    private static boolean caveIsNotStartOrEnd(Cave cave) {
        return !cave.getName().equals("start") && !cave.getName().equals("end");
    }

    private static boolean anySmallCaveVisitedTwiceYet(String path) {
        return Arrays.stream(path.split(","))
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getKey().equals(e.getKey().toLowerCase()))
                .noneMatch(e -> e.getValue() > 1);
    }
}
