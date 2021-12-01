package com.advent.of.code.day1;

import java.util.ArrayList;
import java.util.List;

public class ReportProcessor {
    public int countIncreases(List<Integer> measurements) {
        int result = 0;
        for (int i = 1; i < measurements.size(); i++) {
            if (measurements.get(i) > measurements.get(i - 1)) {
                result++;
            }
        }
        return result;
    }

    public int countIncreasesWithSlidingWindow(List<Integer> measurements, int windowWidth) {
        final List<Integer> windowsMeasurements = new ArrayList<>();
        for (int i = 0; i < measurements.size() - windowWidth + 1; i++) {
            int windowValue = 0;
            for (int j = 0; j < windowWidth; j++) {
                windowValue += measurements.get(i + j);
            }
            windowsMeasurements.add(windowValue);
        }
        return countIncreases(windowsMeasurements);
    }
}
