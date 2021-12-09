package com.advent.of.code.day9;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class LowPointProcessorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperSumOfLowPointsRiskLevels(String inputFilePath, int expectedValue) {
        // given
        final LowPointProcessor processor = new LowPointProcessor();
        final List<String> inputLines = readFileLines(inputFilePath);

        int width = inputLines.size();
        int height = inputLines.get(0).length();
        final int[][] input = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                input[i][j] = Integer.parseInt(inputLines.get(i).substring(j, j + 1));
            }
        }

        // when
        final int sum = processor.getSumOfRiskLevels(input);

        // then
        Assertions.assertThat(sum).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperSumOfThreeBiggestBasins(String inputFilePath, int expectedValue) {
        // given
        final BasinProcessor processor = new BasinProcessor();
        final List<String> inputLines = readFileLines(inputFilePath);

        int width = inputLines.size();
        int height = inputLines.get(0).length();
        final Location[][] input = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int locationHeight = Integer.parseInt(inputLines.get(i).substring(j, j + 1));
                input[i][j] = new Location(i, j, locationHeight);
            }
        }

        // when
        final int result = processor.getResultOfThreeBiggestBasins(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }


    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day9/test.in", 15),
                Arguments.of("src/test/resources/day9/input1.in", 465)
        );
    }
    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day9/test.in", 1134),
                Arguments.of("src/test/resources/day9/input1.in", 1269555)
        );
    }
}