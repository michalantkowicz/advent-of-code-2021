package com.advent.of.code.day7;

import java.util.List;
import java.util.function.BiFunction;

import static java.util.Comparator.comparingInt;

class CrabAdjuster {
    int calculateAdjustmentCostForFixedBurnRate(List<Integer> positions) {
        return calculateCost(positions, (currentPosition, positionCandidate) -> Math.abs(currentPosition - positionCandidate));
    }

    int calculateAdjustmentCostForIncreasingBurnRate(List<Integer> positions) {
        return calculateCost(positions, (currentPosition, positionCandidate) -> {
            int n = Math.abs(currentPosition - positionCandidate);
            return ((1 + n) * n) / 2;
        });
    }

    private int calculateCost(List<Integer> positions, BiFunction<Integer, Integer, Integer> currentCostCalculator) {
        final int minPosition = positions.stream().min(comparingInt(Integer::intValue)).get();
        final int maxPosition = positions.stream().max(comparingInt(Integer::intValue)).get();

        int minCost = Integer.MAX_VALUE;

        for (int positionCandidate = minPosition; positionCandidate <= maxPosition; positionCandidate++) {
            int currentCost = 0;
            for (Integer currentPosition : positions) {
                currentCost += currentCostCalculator.apply(currentPosition, positionCandidate);
            }
            if (currentCost < minCost) {
                minCost = currentCost;
            }
        }

        return minCost;
    }
}
