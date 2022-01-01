package com.advent.of.code.day8;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

class DecoderUtil {
    static String findOne(List<String> input) {
        return findByLength(input, 2).get(0);
    }

    static String findFour(List<String> input) {
        return findByLength(input, 4).get(0);
    }

    static String findSeven(List<String> input) {
        return findByLength(input, 3).get(0);
    }

    static String findEight(List<String> input) {
        return findByLength(input, 7).get(0);
    }

    static String findThree(List<String> input) {
        final String one = findOne(input);
        final String four = findFour(input);
        return findByLength(input, 5).stream()
                .filter(s -> containsNCharactersOf(s, 3, four))
                .filter(s -> containsNCharactersOf(s, 2, one))
                .findFirst()
                .get();
    }

    static String findTwo(List<String> input) {
        final String four = findFour(input);
        return findByLength(input, 5).stream()
                .filter(s -> !s.equals(findThree(input)))
                .filter(s -> containsNCharactersOf(s, 2, four))
                .findFirst()
                .get();
    }

    static String findFive(List<String> input) {
        return findByLength(input, 5).stream()
                .filter(s -> !s.equals(findThree(input)))
                .filter(s -> !s.equals(findTwo(input)))
                .findFirst()
                .get();
    }

    static String findNine(List<String> input) {
        final String three = findThree(input);
        return findByLength(input, 6).stream()
                .filter(s -> containsNCharactersOf(s, 5, three))
                .findFirst()
                .get();
    }

    static String findSix(List<String> input) {
        final String five = findFive(input);
        return findByLength(input, 6).stream()
                .filter(s -> !s.equals(findNine(input)))
                .filter(s -> containsNCharactersOf(s, 5, five))
                .findFirst()
                .get();
    }

    static String findZero(List<String> input) {
        return findByLength(input, 6).stream()
                .filter(s -> !s.equals(findSix(input)))
                .filter(s -> !s.equals(findNine(input)))
                .findFirst()
                .get();
    }

    static List<String> subtract(String first, String second) {
        final HashSet<String> firstSet = new HashSet<>(asList(first.split("")));
        final HashSet<String> secondSet = new HashSet<>(asList(second.split("")));
        return firstSet.stream().filter(s -> !secondSet.contains(s)).collect(Collectors.toList());
    }

    static boolean test(String value, int expectedLength, String... expectedSegments) {
        return value.length() == expectedLength && subtract(value, String.join("", expectedSegments)).size() == 0;
    }

    /**
     * Checks whether value contains exactly n characters of compareValue
     */
    private static boolean containsNCharactersOf(String value, int n, String compareValue) {
        return (value.length() - subtract(value, compareValue).size()) == n;
    }

    private static List<String> findByLength(List<String> input, int length) {
        return input.stream().filter(i -> i.length() == length).collect(Collectors.toList());
    }
}
