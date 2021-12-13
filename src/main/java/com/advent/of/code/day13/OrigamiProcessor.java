package com.advent.of.code.day13;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class OrigamiProcessor {
    Set<Point> foldPaper(Set<Point> initialPoints, List<FoldCommand> commands, int steps) {
        Set<Point> result = new HashSet<>(initialPoints);
        for (int step = 0; step < steps; step++) {
            final FoldCommand command = commands.get(step);
            result = result.stream().map(command::process).collect(Collectors.toSet());
        }
        return result;
    }
}