package com.advent.of.code.day11;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class FlashCalculatorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperCountOfFlashesAfter100Steps(String inputFilePath, long expectedValue) {
        // given
        final FlashCalculator calculator = new FlashCalculator();
        final List<String> inputLines = readFileLines(inputFilePath);
        final Octopus[][] input = prepareInput(inputLines);

        // when
        final long result = calculator.countFlashesAfter(100, input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldFindTheFirstStepAtWhichAllOctopusesFlashSynchronously(String inputFilePath, long expectedValue) {
        // given
        final FlashCalculator calculator = new FlashCalculator();
        final List<String> inputLines = readFileLines(inputFilePath);
        final Octopus[][] input = prepareInput(inputLines);
        int octopusesCount = input.length * input[0].length;

        // when
        final long result = calculator.findFirstSynchronousFlash(octopusesCount, input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private Octopus[][] prepareInput(List<String> inputLines) {
        int width = inputLines.size();
        int height = inputLines.get(0).length();
        final Octopus[][] input = new Octopus[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int octopusEnergy = Integer.parseInt(inputLines.get(i).substring(j, j + 1));
                input[i][j] = new Octopus(new Position(i, j), octopusEnergy, width, height);
            }
        }
        return input;
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day11/test.in", 1656L),
                Arguments.of("src/test/resources/day11/input1.in", 1743L)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day11/test.in", 195L),
                Arguments.of("src/test/resources/day11/input1.in", 364L)
        );
    }
}