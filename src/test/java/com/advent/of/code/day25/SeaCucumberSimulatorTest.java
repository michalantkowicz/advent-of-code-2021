package com.advent.of.code.day25;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class SeaCucumberSimulatorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateFirstStepWithoutMovementNumber(String inputFilePath, long expectedValue) {
        // given
        final SeaCucumberSimulator simulator = new SeaCucumberSimulator();
        final List<String> inputLines = readFileLines(inputFilePath);
        final SeaCucumber[][] input = parseInput(inputLines);

        // when
        final long result = simulator.getStepWithoutMovement(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private SeaCucumber[][] parseInput(List<String> lines) {
        final int height = lines.size();
        final int width = lines.get(0).length();
        final SeaCucumber[][] result = new SeaCucumber[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result[x][y] = switch (lines.get(y).charAt(x)) {
                    case '>' -> new SeaCucumber(SeaCucumberType.EAST);
                    case 'v' -> new SeaCucumber(SeaCucumberType.SOUTH);
                    default -> null;
                };
            }
        }

        return result;
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day25/test.in", 58L),
                Arguments.of("src/test/resources/day25/input1.in", 412L)
        );
    }
}