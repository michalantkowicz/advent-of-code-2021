package com.advent.of.code.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class DiagnosticUnit {
    int calculatePowerConsumption(List<List<Integer>> report) {
        final int[] mostCommonBits = findMostCommonBits(report);
        final int[] leastCommonBits = findLeastCommonBits(report);
        return fromBinary(mostCommonBits) * fromBinary(leastCommonBits);
    }

    int calculateLifeSupportingRating(List<List<Integer>> report) {
        final int[] oxygenGeneratorRating = filterValue(report, (candidates, bitPosition) -> {
                    final int[] mostCommonBits = findMostCommonBits(candidates);
                    return candidates.stream().filter(candidateRow ->
                            candidateRow.get(bitPosition) == mostCommonBits[bitPosition]
                    ).collect(Collectors.toList());
                }
        );
        final int[] co2ScrubberRating = filterValue(report, (candidates, bitPosition) -> {
                    final int[] leastCommonBits = findLeastCommonBits(candidates);
                    return candidates.stream().filter(candidateRow ->
                            candidateRow.get(bitPosition) == leastCommonBits[bitPosition]
                    ).collect(Collectors.toList());
                }
        );
        return fromBinary(oxygenGeneratorRating) * fromBinary(co2ScrubberRating);
    }

    private int[] filterValue(List<List<Integer>> values, BiFunction<List<List<Integer>>, Integer, List<List<Integer>>> filter) {
        final int rowLength = getRowLength(values);
        final int[] value = new int[rowLength];
        List<List<Integer>> candidates = new ArrayList<>(values);
        for (int currentBitPosition = 0; currentBitPosition < rowLength; currentBitPosition++) {
            if (candidates.size() < 1) {
                throw new UnsupportedOperationException("Result oxygenCandidates list must contain one element");
            } else if (candidates.size() > 1) {
                candidates = filter.apply(candidates, currentBitPosition);
            } else {
                break;
            }
        }
        for (int i = 0; i < rowLength; i++) {
            value[i] = candidates.get(0).get(i);
        }
        return value;
    }

    private int[] findMostCommonBits(List<List<Integer>> values) {
        final int rowLength = getRowLength(values);
        final int[] mostCommonBits = new int[rowLength];
        final int[] result = sumBits(values);
        for (int i = 0; i < rowLength; i++) {
            mostCommonBits[i] = (result[i] < values.size() / 2f) ? 0 : 1;
        }
        return mostCommonBits;
    }

    private int[] findLeastCommonBits(List<List<Integer>> values) {
        final int rowLength = getRowLength(values);
        final int[] leastCommonBits = new int[rowLength];
        final int[] result = sumBits(values);
        for (int i = 0; i < rowLength; i++) {
            leastCommonBits[i] = (result[i] < values.size() / 2f) ? 1 : 0;
        }
        return leastCommonBits;
    }

    private int[] sumBits(List<List<Integer>> values) {
        final int rowLength = getRowLength(values);
        final int[] result = new int[rowLength];
        Arrays.fill(result, 0);
        values.forEach(row -> {
                    for (int i = 0; i < rowLength; i++) {
                        result[i] += row.get(i);
                    }
                }
        );
        return result;
    }

    private int fromBinary(int[] bits) {
        int result = 0;
        for (int i = 0; i < bits.length; i++) {
            int bitPosition = bits.length - 1 - i;
            result |= (bits[i] << bitPosition);
        }
        return result;
    }

    private int getRowLength(List<List<Integer>> report) {
        return report.get(0).size();
    }
}
