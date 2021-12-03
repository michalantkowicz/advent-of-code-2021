package com.advent.of.code.day2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class CommandProcessorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProper2DPositionAfterTestCommands(String inputFilePath, int expectedValue) {
        // given
        final CommandProcessor processor = new CommandProcessor();
        final Position initialPosition = new Position(0, 0, 0);
        final CommandMapper2D mapper = new CommandMapper2D();
        final List<Command> input = readFileLines(inputFilePath).stream()
                .map(mapper::positionFrom)
                .collect(Collectors.toList());

        // when
        final Position finalPosition = processor.process(initialPosition, input);

        // then
        Assertions.assertThat(finalPosition.depth() * finalPosition.horizontal()).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProper3DPositionAfterTestCommands(String inputFilePath, int expectedValue) {
        // given
        final CommandProcessor processor = new CommandProcessor();
        final Position initialPosition = new Position(0, 0, 0);
        final CommandMapper3D mapper = new CommandMapper3D();
        final List<Command> input = readFileLines(inputFilePath).stream()
                .map(mapper::positionFrom)
                .collect(Collectors.toList());

        // when
        final Position finalPosition = processor.process(initialPosition, input);

        // then
        Assertions.assertThat(finalPosition.depth() * finalPosition.horizontal()).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day2/test.in", 150),
                Arguments.of("src/test/resources/day2/input1.in", 1635930)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day2/test.in", 900),
                Arguments.of("src/test/resources/day2/input2.in", 1781819478)
        );
    }
}