package com.advent.of.code.day17;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static java.lang.Integer.parseInt;

class ProbeShooterTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateTheHighestPoint(String inputFilePath, int expectedValue) {
        // given
        final ProbeShooter shooter = new ProbeShooter();
        final String inputLine = readFileLines(inputFilePath).get(0);
        final String[] x = inputLine.split("=")[1].split(",")[0].trim().split("\\.\\.");
        final String[] y = inputLine.split("=")[2].trim().split("\\.\\.");
        final Target input = new Target(parseInt(x[0]), parseInt(y[0]), parseInt(x[1]), parseInt(y[1]));

        // when
        final int result = shooter.simulateAndFindHighestPoint(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateCountOfProperInitialVelocities(String inputFilePath, int expectedValue) {
        // given
        final ProbeShooter shooter = new ProbeShooter();
        final String inputLine = readFileLines(inputFilePath).get(0);
        final String[] x = inputLine.split("=")[1].split(",")[0].trim().split("\\.\\.");
        final String[] y = inputLine.split("=")[2].trim().split("\\.\\.");
        final Target input = new Target(parseInt(x[0]), parseInt(y[0]), parseInt(x[1]), parseInt(y[1]));

        // when
        final int result = shooter.simulateAndFindProperInitialVelocitiesCount(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day17/test.in", 45),
                Arguments.of("src/test/resources/day17/input1.in", 3160)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day17/test.in", 112),
                Arguments.of("src/test/resources/day17/input1.in", 1928)
        );
    }
}