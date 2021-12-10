package com.advent.of.code.day9;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class SmokeFlowRadarTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperSumOfLowPointsRiskLevels(String inputFilePath, int expectedValue) {
        // given
        final SmokeFlowRadar radar = new SmokeFlowRadar();
        final List<String> inputLines = readFileLines(inputFilePath);

        final Location[][] input = prepareInput(inputLines);

        // when
        final int sum = radar.getSumOfRiskLevels(input);

        // then
        Assertions.assertThat(sum).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperSumOfThreeBiggestBasins(String inputFilePath, int expectedValue) {
        // given
        final SmokeFlowRadar radar = new SmokeFlowRadar();
        final List<String> inputLines = readFileLines(inputFilePath);
        final Location[][] input = prepareInput(inputLines);

        // when
        final int result = radar.getResultOfThreeBiggestBasins(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private Location[][] prepareInput(List<String> inputLines) {
        int width = inputLines.size();
        int height = inputLines.get(0).length();
        final Location[][] input = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int locationHeight = Integer.parseInt(inputLines.get(i).substring(j, j + 1));
                input[i][j] = new Location(new Position(i, j), locationHeight);
            }
        }
        return input;
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