package com.advent.of.code.day3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static java.util.Arrays.stream;

class DiagnosticUnitTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperPowerConsumption(String inputFilePath, int expectedValue) {
        // given
        final DiagnosticUnit diagnosticUnit = new DiagnosticUnit();
        final List<List<Integer>> input = readFileLines(inputFilePath).stream()
                .map(row -> {
                    return stream(row.split("")).map(Integer::valueOf).collect(Collectors.toList());
                })
                .collect(Collectors.toList());

        // when
        final int powerConsumption = diagnosticUnit.calculatePowerConsumption(input);

        // then
        Assertions.assertThat(powerConsumption).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day3/test.in", 198),
                Arguments.of("src/test/resources/day3/input1.in", 2972336)
        );
    }
}