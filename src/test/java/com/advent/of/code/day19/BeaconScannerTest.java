package com.advent.of.code.day19;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static java.lang.Integer.parseInt;

class BeaconScannerTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperCountOfBeacons(String inputFilePath, int expectedValue) {
        // given
        final BeaconScanner scanner = new BeaconScanner();
        final List<String> inputLines = readFileLines(inputFilePath);
        final List<ScannerArea> input = parseLinesToAreas(inputLines);
        // when
        final int result = scanner.calculateAllPointsCount(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperMaxManhattanDistanceBetweenScanners(String inputFilePath, int expectedValue) {
        // given
        final BeaconScanner scanner = new BeaconScanner();
        final List<String> inputLines = readFileLines(inputFilePath);
        final List<ScannerArea> input = parseLinesToAreas(inputLines);
        // when
        final int result = scanner.getMaxDistanceBetweenScanners(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private List<ScannerArea> parseLinesToAreas(List<String> inputLines) {
        final List<ScannerArea> areas = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for (String line : inputLines) {
            if (line.startsWith("---")) {
                points = new ArrayList<>();
            } else if (line.isEmpty() || line.isBlank()) {
                areas.add(new ScannerArea(points));
            } else {
                final String[] split = line.split(",");
                final Point point = new Point(parseInt(split[0]), parseInt(split[1]), parseInt(split[2]));
                points.add(point);
            }
        }
        areas.add(new ScannerArea(points));
        return areas;
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day19/test.in", 79),
                Arguments.of("src/test/resources/day19/input1.in", 381)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day19/test.in", 3621),
                Arguments.of("src/test/resources/day19/input1.in", 12201)
        );
    }
}