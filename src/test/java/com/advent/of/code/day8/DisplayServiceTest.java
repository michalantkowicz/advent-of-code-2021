package com.advent.of.code.day8;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class DisplayServiceTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateOccurrencesOf1478(String inputFilePath, int expectedValue) {
        // given
        final DisplayService service = new DisplayService();
        final List<DisplayEntry> input = readFileLines(inputFilePath).stream()
                .map(line -> {
                    final String[] parts = line.split(" \\| ");
                    final List<String> patterns = Arrays.asList(parts[0].trim().split(" "));
                    final List<String> outputs = Arrays.asList(parts[1].trim().split(" "));
                    return new DisplayEntry(patterns, outputs);
                })
                .collect(Collectors.toList());

        // when
        final int count = service.count1478Occurrences(input);

        // then
        Assertions.assertThat(count).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldSumUpDecodedValues(String inputFilePath, long expectedValue) {
        // given
        final DisplayService service = new DisplayService();
        final List<DisplayEntry> input = readFileLines(inputFilePath).stream()
                .map(line -> {
                    final String[] parts = line.split(" \\| ");
                    final List<String> patterns = Arrays.asList(parts[0].trim().split(" "));
                    final List<String> outputs = Arrays.asList(parts[1].trim().split(" "));
                    return new DisplayEntry(patterns, outputs);
                })
                .collect(Collectors.toList());

        // when
        final long sum = service.decodeAndSumOutputs(input);

        // then
        Assertions.assertThat(sum).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day8/test.in", 26),
                Arguments.of("src/test/resources/day8/input1.in", 247)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day8/test.in", 61229L),
                Arguments.of("src/test/resources/day8/input1.in", 933305L)
        );
    }
    
    // 1160441 too high
}