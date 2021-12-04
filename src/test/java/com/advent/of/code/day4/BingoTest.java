package com.advent.of.code.day4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;

class BingoTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperFinalScore(String inputFilePath, int expectedValue) {
        // given
        final Bingo bingo = new Bingo();
        final List<Integer> numbers = loadNumbers(inputFilePath);
        final List<Board> boards = loadBoards(inputFilePath);

        // when
        final int finalScore = bingo.play(numbers, boards);

        // then
        Assertions.assertThat(finalScore).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperFinalScoreWhenLost(String inputFilePath, int expectedValue) {
        // given
        final Bingo bingo = new Bingo();
        final List<Integer> numbers = loadNumbers(inputFilePath);
        final List<Board> boards = loadBoards(inputFilePath);

        // when
        final int finalScore = bingo.playAndFail(numbers, boards);

        // then
        Assertions.assertThat(finalScore).isEqualTo(expectedValue);
    }

    private List<Integer> loadNumbers(String inputFilePath) {
        return stream(readFileLines(inputFilePath).get(0).split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private List<Board> loadBoards(String inputFilePath) {
        return stream(readFileLines(inputFilePath).stream()
                .skip(1)
                .collect(Collectors.joining("\n"))
                .split("\n\n"))
                .map(boardStr ->
                        stream(boardStr.split("\n"))
                                .filter(not(String::isBlank))
                                .map(boardRowStr ->
                                        stream(boardRowStr.split(" "))
                                                .filter(not(String::isBlank))
                                                .map(String::trim)
                                                .map(Integer::valueOf)
                                                .collect(Collectors.toList())
                                )
                                .collect(Collectors.toList())
                )
                .map(DefaultBoard::new)
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day4/test.in", 4512),
                Arguments.of("src/test/resources/day4/input1.in", 45031)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day4/test.in", 1924),
                Arguments.of("src/test/resources/day4/input1.in", 2568)
        );
    }
}