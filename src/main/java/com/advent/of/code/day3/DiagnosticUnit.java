package com.advent.of.code.day3;

import java.util.Arrays;
import java.util.List;

class DiagnosticUnit {
    public int calculatePowerConsumption(List<List<Integer>> report) {
        final int rowLength = report.get(0).size();
        final int[] result = new int[rowLength];
        Arrays.fill(result, 0);

        report.forEach(row -> {
                    for (int i = 0; i < rowLength; i++) {
                        result[i] += row.get(i);
                    }
                }
        );

        final int[] mostCommonBits = new int[rowLength];
        final int[] leastCommonBits = new int[rowLength];

        for (int i = 0; i < rowLength; i++) {
            mostCommonBits[i] = (result[i] < report.size() / 2f) ? 0 : 1;
            leastCommonBits[i] = (result[i] < report.size() / 2f) ? 1 : 0;
        }

        return fromBinary(mostCommonBits) * fromBinary(leastCommonBits);
    }

    private int fromBinary(int[] bits) {
        int result = 0;
        for (int i = 0; i < bits.length; i++) {
            int bitPosition = bits.length - 1 - i;
            result |= (bits[i] << bitPosition);
        }
        return result;
    }
}
