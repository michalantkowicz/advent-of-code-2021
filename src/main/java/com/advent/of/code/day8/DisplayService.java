package com.advent.of.code.day8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.advent.of.code.day8.DecoderUtil.findEight;
import static com.advent.of.code.day8.DecoderUtil.findFive;
import static com.advent.of.code.day8.DecoderUtil.findFour;
import static com.advent.of.code.day8.DecoderUtil.findNine;
import static com.advent.of.code.day8.DecoderUtil.findOne;
import static com.advent.of.code.day8.DecoderUtil.findSeven;
import static com.advent.of.code.day8.DecoderUtil.findSix;
import static com.advent.of.code.day8.DecoderUtil.findThree;
import static com.advent.of.code.day8.DecoderUtil.findTwo;
import static com.advent.of.code.day8.DecoderUtil.findZero;
import static com.advent.of.code.day8.DecoderUtil.subtract;
import static com.advent.of.code.day8.DecoderUtil.test;

class DisplayService {
    int count1478Occurrences(List<DisplayEntry> entries) {
        return (int) entries.stream()
                .map(DisplayEntry::outputs)
                .flatMap(Collection::stream)
                .filter(o -> Arrays.asList(2, 3, 4, 7).contains(o.length()))
                .count();
    }

    long decodeAndSumOutputs(List<DisplayEntry> entries) {
        return entries.stream().mapToInt(this::decodeEntry).sum();
    }

    /**
     * .aa.
     * b  c
     * .dd.
     * e  f
     * .gg.
     */
    private int decodeEntry(DisplayEntry entry) {
        final List<String> patterns = entry.patterns();
        final String segmentA = subtract(findSeven(patterns), findOne(patterns)).get(0);
        final String segmentB = subtract(findFour(patterns), findThree(patterns)).get(0);
        final String segmentC = subtract(findEight(patterns), findSix(patterns)).get(0);
        final String segmentD = subtract(findEight(patterns), findZero(patterns)).get(0);
        final String segmentE = subtract(findEight(patterns), findNine(patterns)).get(0);
        final String segmentF = subtract(findFive(patterns), findTwo(patterns)).stream()
                .filter(s -> !s.equals(segmentB))
                .findFirst().get();
        final String segmentG = subtract(findZero(patterns), findSeven(patterns)).stream()
                .filter(s -> !(s.equals(segmentB) || s.equals(segmentE)))
                .findFirst().get();

        return Integer.parseInt(entry.outputs().stream()
                .map(encodedOutput -> {
                    if (test(encodedOutput, 2, segmentC, segmentF)) return "1";
                    else if (test(encodedOutput, 5, segmentA, segmentC, segmentD, segmentE, segmentG)) return "2";
                    else if (test(encodedOutput, 5, segmentA, segmentC, segmentD, segmentF, segmentG)) return "3";
                    else if (test(encodedOutput, 4, segmentB, segmentC, segmentD, segmentF)) return "4";
                    else if (test(encodedOutput, 5, segmentA, segmentB, segmentD, segmentF, segmentG)) return "5";
                    else if (test(encodedOutput, 6, segmentA, segmentB, segmentD, segmentE, segmentF, segmentG)) return "6";
                    else if (test(encodedOutput, 3, segmentA, segmentC, segmentF)) return "7";
                    else if (test(encodedOutput, 7, segmentA, segmentA, segmentB, segmentC, segmentD, segmentE, segmentF, segmentG)) return "8";
                    else if (test(encodedOutput, 6, segmentA, segmentA, segmentB, segmentC, segmentD, segmentF, segmentG)) return "9";
                    else if (test(encodedOutput, 6, segmentA, segmentA, segmentB, segmentC, segmentE, segmentF, segmentG)) return "0";
                    else throw new IllegalStateException("Value " + encodedOutput + " cannot be decoded!");
                })
                .collect(Collectors.joining()));
    }
}
