package com.advent.of.code.day5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class VentsRadarTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperCountOfOverlappingPointsForHorizontalAndVertical(String inputFilePath, long expectedValue) {
        // given
        final VentsRadar ventsRadar = new VentsRadar();
        final List<Segment> input = readFileLines(inputFilePath).stream()
                .map(row -> {
                    final String firstPointStr = row.split(" -> ")[0];
                    final String secondPointStr = row.split(" -> ")[1];
                    return new Segment(
                            Integer.parseInt(firstPointStr.split(",")[0]),
                            Integer.parseInt(firstPointStr.split(",")[1]),
                            Integer.parseInt(secondPointStr.split(",")[0]),
                            Integer.parseInt(secondPointStr.split(",")[1])
                    );
                })
                .filter(this::isVericalOrHorizontal)
                .collect(Collectors.toList());

        // when
        final long intersectionsCount = ventsRadar.getIntersectionsOf(input, 2);

        // then
        Assertions.assertThat(intersectionsCount).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperCountOfOverlappingPointsForDiagonalsToo(String inputFilePath, long expectedValue) {
        // given
        final VentsRadar ventsRadar = new VentsRadar();
        final List<Segment> input = readFileLines(inputFilePath).stream()
                .map(row -> {
                    final String firstPointStr = row.split(" -> ")[0];
                    final String secondPointStr = row.split(" -> ")[1];
                    return new Segment(
                            Integer.parseInt(firstPointStr.split(",")[0]),
                            Integer.parseInt(firstPointStr.split(",")[1]),
                            Integer.parseInt(secondPointStr.split(",")[0]),
                            Integer.parseInt(secondPointStr.split(",")[1])
                    );
                })
                .collect(Collectors.toList());

        // when
        final long intersectionsCount = ventsRadar.getIntersectionsOf(input, 2);

        // then
        Assertions.assertThat(intersectionsCount).isEqualTo(expectedValue);
    }

    private boolean isVericalOrHorizontal(Segment segment) {
        return segment.x1() == segment.x2() || segment.y1() == segment.y2();
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day5/test.in", 5L),
                Arguments.of("src/test/resources/day5/input1.in", 5306L)
        );
    }
    
    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day5/test.in", 12L),
                Arguments.of("src/test/resources/day5/input1.in", 17787L)
        );
    }
}