package com.advent.of.code.day7;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class CrabAdjusterTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateCheapestAlignmentCostWithFixedBurnRate(String inputFilePath, int expectedValue) {
        // given
        final CrabAdjuster adjuster = new CrabAdjuster();
        final List<Integer> input = Arrays.stream(readFileLines(inputFilePath).get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // when
        final int cost = adjuster.calculateAdjustmentCostForFixedBurnRate(input);

        // then
        Assertions.assertThat(cost).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateCheapestAlignmentCostWithIncreasingFuelBurnRate(String inputFilePath, int expectedValue) {
        // given
        final CrabAdjuster adjuster = new CrabAdjuster();
        final List<Integer> input = Arrays.stream(readFileLines(inputFilePath).get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // when
        final int cost = adjuster.calculateAdjustmentCostForIncreasingBurnRate(input);

        // then
        Assertions.assertThat(cost).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day7/test.in", 37),
                Arguments.of("src/test/resources/day7/input1.in", 343605)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day7/test.in", 168),
                Arguments.of("src/test/resources/day7/input1.in", 96744904)
        );
    }
}