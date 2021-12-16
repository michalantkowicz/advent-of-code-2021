package com.advent.of.code.day15;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class CavernScannerTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperCostOfCheapestPath(String inputFilePath, int expectedValue) {
        // given
        final CavernScanner scanner = new CavernScanner();
        final List<String> inputLines = readFileLines(inputFilePath);
        final CavernPoint[][] input = prepareInput(inputLines);

        // when
        final int result = scanner.getCheapestPathCost(input[0][0], input[input.length - 1][input[0].length - 1]);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperCostOfCheapestPathOnFullMap(String inputFilePath, int expectedValue) {
        // given
        final CavernScanner scanner = new CavernScanner();
        final List<String> inputLines = readFileLines(inputFilePath);
        final CavernPoint[][] tile = prepareInput(inputLines);
        final CavernPoint[][] input = getFullMap(tile);

        // when
        final int result = scanner.getCheapestPathCost(input[0][0], input[input.length - 1][input[0].length - 1]);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private CavernPoint[][] prepareInput(List<String> inputLines) {
        final int width = inputLines.size();
        final int height = inputLines.get(0).length();
        final CavernPoint[][] input = new CavernPoint[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int riskLevel = Integer.parseInt(inputLines.get(i).substring(j, j + 1));
                input[i][j] = new CavernPoint(new Position(i, j), riskLevel, (i == 0 && j == 0) ? 0 : 10_000_000);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (Position p : input[i][j].getPosition().getAdjacents(0, input.length, 0, input[0].length)) {
                    input[i][j].addAdjacent(input[p.x()][p.y()]);
                }
            }
        }
        return input;
    }

    private CavernPoint[][] getFullMap(CavernPoint[][] input) {
        final int initWidth = input.length;
        final int initHeight = input[0].length;
        final int width = input.length * 5;
        final int height = input[0].length * 5;
        final CavernPoint[][] fullMap = new CavernPoint[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = i % initWidth;
                int y = j % initHeight;
                int increaseBy = (i / initWidth) + (j / initHeight);
                int newRiskLevel = input[x][y].getRiskLevel() + increaseBy;
                while (newRiskLevel > 9) newRiskLevel -= 9;
                fullMap[i][j] = new CavernPoint(new Position(i, j), newRiskLevel, (i == 0 && j == 0) ? 0 : 10_000_000);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (Position p : fullMap[i][j].getPosition().getAdjacents(0, fullMap.length, 0, fullMap[0].length)) {
                    fullMap[i][j].addAdjacent(fullMap[p.x()][p.y()]);
                }
            }
        }
        return fullMap;
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day15/test.in", 40),
                Arguments.of("src/test/resources/day15/input1.in", 366)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day15/test.in", 315),
                Arguments.of("src/test/resources/day15/input1.in", 2829)
        );
    }
}