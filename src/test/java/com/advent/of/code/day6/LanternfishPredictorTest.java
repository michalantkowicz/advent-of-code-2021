package com.advent.of.code.day6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class LanternfishPredictorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperPopulationCount(String inputFilePath, int atDay, long expectedValue) {
        // given
        final LanternfishPredictor predictor = new LanternfishPredictor();
        final var input = Arrays.stream(readFileLines(inputFilePath).stream()
                        .findFirst()
                        .get()
                        .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

        // when
        final long populationCount = predictor.predictPopulationAfter(atDay, input);

        // then
        Assertions.assertThat(populationCount).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day6/test.in", 80, 5934L),
                Arguments.of("src/test/resources/day6/input1.in", 80, 372984L),
                Arguments.of("src/test/resources/day6/test.in", 256, 26984457539L),
                Arguments.of("src/test/resources/day6/input1.in", 256, 1681503251694L)
        );
    }
}