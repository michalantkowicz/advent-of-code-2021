package com.advent.of.code.day18;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class SnailfishCalculatorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperSumAndThenProperMagnitude(String inputFilePath, int expectedValue) {
        // given
        final SnailfishCalculator calculator = new SnailfishCalculator();
        final List<String> inputLines = readFileLines(inputFilePath);

        // when
        final int result = calculator.calculateMagnitudeForSumOf(inputLines);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateMaxMagnitudeSummingTwoOfNumbers(String inputFilePath, int expectedValue) {
        // given
        final SnailfishCalculator calculator = new SnailfishCalculator();
        final List<String> inputLines = readFileLines(inputFilePath);

        // when
        final int result = calculator.calculateMaxMagnitudeSummingTwoOf(inputLines);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day18/test.in", 4140),
                Arguments.of("src/test/resources/day18/input1.in", 4184)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day18/test.in", 3993),
                Arguments.of("src/test/resources/day18/input1.in", 4731)
        );
    }

    // ---

    @Disabled
    @ParameterizedTest
    @MethodSource("provideArgumentsForExplode")
    void test_shouldExplode(String numberToExplode, String expectedNumberAfterExplode) {
        // given

        // when
        final String exploded = new SnailfishCalculator().explode(numberToExplode);

        // then
        Assertions.assertThat(exploded).isEqualTo(expectedNumberAfterExplode);
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("provideArgumentsForSplit")
    void test_shouldSplit(String numberToSplit, String expectedNumberAfterSplit) {
        // given

        // when
        final String exploded = new SnailfishCalculator().split(numberToSplit);

        // then
        Assertions.assertThat(exploded).isEqualTo(expectedNumberAfterSplit);
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("provideArgumentsForSum")
    void test_shouldSum(String first, String second, String expectedResult) {
        // given

        // when
        final String sum = new SnailfishCalculator().sum(List.of(first, second));

        // then
        Assertions.assertThat(sum).isEqualTo(expectedResult);
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("provideArgumentsForMagnitude")
    void test_shouldCalculateProperMagnitude(String number, int expectedMagnitude) {
        // given

        // when
        final int magnitude = new SnailfishCalculator().calculateMagnitude(number);

        // then
        Assertions.assertThat(magnitude).isEqualTo(expectedMagnitude);
    }

    private static Stream<Arguments> provideArgumentsForExplode() {
        return Stream.of(
                Arguments.of("[[[[[9,8],1],2],3],4]", "[[[[0,9],2],3],4]"),
                Arguments.of("[7,[6,[5,[4,[3,2]]]]]", "[7,[6,[5,[7,0]]]]"),
                Arguments.of("[[6,[5,[4,[3,2]]]],1]", "[[6,[5,[7,0]]],3]"),
                Arguments.of("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]", "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"),
                Arguments.of("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", "[[3,[2,[8,0]]],[9,[5,[7,0]]]]")
        );
    }

    private static Stream<Arguments> provideArgumentsForSplit() {
        return Stream.of(
                Arguments.of("[[[[0,7],4],[15,[0,13]]],[1,1]]", "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"),
                Arguments.of("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]")
        );
    }

    private static Stream<Arguments> provideArgumentsForSum() {
        return Stream.of(
                Arguments.of(
                        "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
                        "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
                        "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"
                ),
                Arguments.of(
                        "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
                        "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
                        "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]"
                ),
                Arguments.of(
                        "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]",
                        "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
                        "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]"
                ),
                Arguments.of(
                        "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]",
                        "[7,[5,[[3,8],[1,4]]]]",
                        "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]"
                ),
                Arguments.of(
                        "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]",
                        "[[2,[2,2]],[8,[8,1]]]",
                        "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]"
                ),
                Arguments.of(
                        "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]",
                        "[2,9]",
                        "[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]"
                ),
                Arguments.of(
                        "[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]",
                        "[1,[[[9,3],9],[[9,0],[0,7]]]]",
                        "[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]"
                ),
                Arguments.of(
                        "[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]",
                        "[[[5,[7,4]],7],1]",
                        "[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]"
                ),
                Arguments.of(
                        "[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]",
                        "[[[[4,2],2],6],[8,7]]",
                        "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
                )
        );
    }

    private static Stream<Arguments> provideArgumentsForMagnitude() {
        return Stream.of(
                Arguments.of("[[1,2],[[3,4],5]]", 143),
                Arguments.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", 1384),
                Arguments.of("[[[[1,1],[2,2]],[3,3]],[4,4]]", 445),
                Arguments.of("[[[[3,0],[5,3]],[4,4]],[5,5]]", 791),
                Arguments.of("[[[[5,0],[7,4]],[5,5]],[6,6]]", 1137),
                Arguments.of("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", 3488)
        );
    }
}