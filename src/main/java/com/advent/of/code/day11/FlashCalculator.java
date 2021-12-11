package com.advent.of.code.day11;

class FlashCalculator {
    long countFlashesAfter(int steps, Octopus[][] octopuses) {
        long flashCount = 0;
        for (int step = 1; step <= steps; step++) {
            for (int i = 0; i < octopuses.length; i++) {
                for (int j = 0; j < octopuses[0].length; j++) {
                    final Octopus octopus = octopuses[i][j];
                    if (octopus.tryFlash(step)) {
                        flashCount++;
                        flashCount += tryFlashAdjacents(step, octopuses, octopus);
                    }
                }
            }
        }
        return flashCount;
    }

    private long tryFlashAdjacents(int currentStep, Octopus[][] octopuses, Octopus octopus) {
        long result = 0;
        for (Position adjacentPosition : octopus.getAdjacents()) {
            final Octopus adjacent = octopuses[adjacentPosition.x()][adjacentPosition.y()];
            if (adjacent.tryFlash(currentStep)) {
                result += 1 + tryFlashAdjacents(currentStep, octopuses, adjacent);
            }
        }
        return result;
    }

    public long findFirstSynchronousFlash(int expectedFlashCount, Octopus[][] octopuses) {
        for (int step = 1; step != 0; step++) {
            int currentFlashCount = 0;
            for (int i = 0; i < octopuses.length; i++) {
                for (int j = 0; j < octopuses[0].length; j++) {
                    final Octopus octopus = octopuses[i][j];
                    if (octopus.tryFlash(step)) {
                        currentFlashCount++;
                        currentFlashCount += tryFlashAdjacents(step, octopuses, octopus);
                    }
                }
            }
            if (currentFlashCount == expectedFlashCount) {
                return step;
            }
        }
        throw new IllegalStateException();
    }
}
