package com.advent.of.code.day10;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class SyntaxCheckerTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperSyntaxErrorScore(String inputFilePath, int expectedValue) {
        // given
        final SyntaxChecker checker = new SyntaxChecker();
        final List<String> input = readFileLines(inputFilePath);

        // when
        final int result = checker.countScoreOfCorruptedLines(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperMiddleScore(String inputFilePath, long expectedValue) {
        // given
        final SyntaxChecker checker = new SyntaxChecker();
        final List<String> input = readFileLines(inputFilePath);

        // when
        final long result = checker.getMiddleScore(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day10/test.in", 26397),
                Arguments.of("src/test/resources/day10/input1.in", 413733)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day10/test.in", 288957L),
                Arguments.of("src/test/resources/day10/input1.in", 3354640192L)
        );
    }
}