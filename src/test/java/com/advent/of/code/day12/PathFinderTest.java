package com.advent.of.code.day12;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class PathFinderTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldFindProperCountOfAllPathsValidDueToRule(String inputFilePath, int expectedValue) {
        // given
        final PathFinder finder = new PathFinder();
        final List<String> inputLines = readFileLines(inputFilePath);
        final Cave start = buildCaveSystem(inputLines);

        // when
        final int count = finder.findAllPathsDueToRule(start, Rules::smallCaveVisitedOnce).size();

        // then
        Assertions.assertThat(count).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldFindProperCountOfAllPathsValidDueToNewRule(String inputFilePath, int expectedValue) {
        // given
        final PathFinder finder = new PathFinder();
        final List<String> inputLines = readFileLines(inputFilePath);
        final Cave start = buildCaveSystem(inputLines);

        // when
        final int count = finder.findAllPathsDueToRule(start, Rules::oneOfSmallCavesMayBeVisitedTwice).size();

        // then
        Assertions.assertThat(count).isEqualTo(expectedValue);
    }

    private Cave buildCaveSystem(List<String> inputLines) {
        final HashMap<String, Cave> caves = new HashMap<>();
        for (String line : inputLines) {
            final Cave firstCave = caves.computeIfAbsent(line.split("-")[0], Cave::new);
            final Cave secondCave = caves.computeIfAbsent(line.split("-")[1], Cave::new);
            firstCave.addAdjacent(secondCave);
            secondCave.addAdjacent(firstCave);
        }
        if (!caves.containsKey("start") || !caves.containsKey("end")) {
            throw new IllegalArgumentException("Provided cave system description must contain caves named 'start' and 'end'!");
        } else {
            return caves.get("start");
        }
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day12/test1.in", 10),
                Arguments.of("src/test/resources/day12/test2.in", 19),
                Arguments.of("src/test/resources/day12/test3.in", 226),
                Arguments.of("src/test/resources/day12/input1.in", 4011)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day12/test1.in", 36),
                Arguments.of("src/test/resources/day12/test2.in", 103),
                Arguments.of("src/test/resources/day12/test3.in", 3509),
                Arguments.of("src/test/resources/day12/input1.in", 108035)
        );
    }
}