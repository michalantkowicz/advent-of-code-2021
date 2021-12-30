package com.advent.of.code.day21;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class DiracDiceGameTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperGameResultWhenPlayingPracticeGame(String inputFilePath, long expectedValue) {
        // given
        final DiracDiceGame game = new DiracDiceGame();
        final List<String> inputLines = readFileLines(inputFilePath);
        final int playerOnePosition = Integer.parseInt(inputLines.get(0).split(":")[1].trim());
        final int playerTwoPosition = Integer.parseInt(inputLines.get(1).split(":")[1].trim());

        // when
        final long result = game.playPracticeGame(playerOnePosition, playerTwoPosition);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperGameResultWhenPlayingRealGame(String inputFilePath, long expectedValue) {
        // given
        final DiracDiceGame game = new DiracDiceGame();
        final List<String> inputLines = readFileLines(inputFilePath);
        final int playerOnePosition = Integer.parseInt(inputLines.get(0).split(":")[1].trim());
        final int playerTwoPosition = Integer.parseInt(inputLines.get(1).split(":")[1].trim());

        // when
        final long result = game.playRealGame(playerOnePosition, playerTwoPosition);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day21/test.in", 739785L),
                Arguments.of("src/test/resources/day21/input1.in", 1004670L)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day21/test.in", 444356092776315L),
                Arguments.of("src/test/resources/day21/input1.in", 492043106122795L)
        );
    }
}