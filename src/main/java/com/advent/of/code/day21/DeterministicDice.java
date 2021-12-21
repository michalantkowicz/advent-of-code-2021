package com.advent.of.code.day21;

import java.util.ArrayList;
import java.util.List;

class DeterministicDice {
    private int rollCount = 0;
    private int value = 1;

    public List<Integer> roll() {
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            rollCount++;
            result.add(value);
            value = (value + 1) % 100;
            value = (value == 0) ? 100 : value;
        }
        return result;
    }

    public int getRollCount() {
        return rollCount;
    }
}
