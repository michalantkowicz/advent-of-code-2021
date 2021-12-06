package com.advent.of.code.day6;

import java.util.HashMap;
import java.util.Map;

class LanternfishPredictor {
    long predictPopulationAfter(int atDay, Map<Integer, Long> individuals) {
        final Map<Integer, Long> skipAtDay = new HashMap<>();
        for (int day = 0; day < atDay; day++) {
            final int dayInCycle = (day % 7) + 1;
            final Map<Integer, Long> updated = new HashMap<>();
            for (int key : individuals.keySet()) {
                if (key - dayInCycle == -1) {
                    final int newKey = (key + 2) % 7;
                    final long currentValue = individuals.getOrDefault(newKey, 0L);
                    final long newValue = currentValue + individuals.get(key) - skipAtDay.getOrDefault(day, 0L);
                    updated.put(key, individuals.get(key));
                    updated.put(newKey, newValue);
                    skipAtDay.put(day + 2, individuals.get(key) - skipAtDay.getOrDefault(day, 0L));
                } else {
                    if (!updated.containsKey(key)) {
                        updated.put(key, individuals.get(key));
                    }
                }
            }
            individuals = updated;
        }
        return individuals.values().stream().mapToLong(Long::longValue).sum();
    }
}
 