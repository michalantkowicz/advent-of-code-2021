package com.advent.of.code.day13;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static java.util.Comparator.comparingInt;

class OrigamiProcessorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperCountOfVisiblePointsAfterFirstStep(String inputFilePath, int expectedValue) {
        // given
        final OrigamiProcessor processor = new OrigamiProcessor();
        final List<String> inputLines = readFileLines(inputFilePath);

        final Set<Point> inputPoints = inputLines.stream()
                .filter(line -> !line.isBlank() && !line.startsWith("fold"))
                .map(line -> new Point(
                                Integer.parseInt(line.split(",")[0]),
                                Integer.parseInt(line.split(",")[1])
                        )
                )
                .collect(Collectors.toSet());

        final List<FoldCommand> inputCommands = inputLines.stream()
                .filter(line -> !line.isBlank() && line.startsWith("fold"))
                .map(line -> {
                            final int value = Integer.parseInt(line.substring(13));
                            if (line.contains("along x=")) return new FoldXCommand(value);
                            else if (line.contains("along y=")) return new FoldYCommand(value);
                            else throw new UnsupportedOperationException("This command is not supported: " + line);
                        }
                ).collect(Collectors.toList());

        // when
        final int result = processor.foldPaper(inputPoints, inputCommands, 1).size();

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldReturnProperActivationCode(String inputFilePath, String expectedCode) {
        // given
        final OrigamiProcessor processor = new OrigamiProcessor();
        final List<String> inputLines = readFileLines(inputFilePath);

        final Set<Point> inputPoints = inputLines.stream()
                .filter(line -> !line.isBlank() && !line.startsWith("fold"))
                .map(line -> new Point(
                                Integer.parseInt(line.split(",")[0]),
                                Integer.parseInt(line.split(",")[1])
                        )
                )
                .collect(Collectors.toSet());

        final List<FoldCommand> inputCommands = inputLines.stream()
                .filter(line -> !line.isBlank() && line.startsWith("fold"))
                .map(line -> {
                            final int value = Integer.parseInt(line.substring(13));
                            if (line.contains("along x=")) return new FoldXCommand(value);
                            else if (line.contains("along y=")) return new FoldYCommand(value);
                            else throw new UnsupportedOperationException("This command is not supported: " + line);
                        }
                ).collect(Collectors.toList());

        // when
        final String result = resultSetToString(processor.foldPaper(inputPoints, inputCommands, inputCommands.size()));

        // then
        Assertions.assertThat(result).isEqualTo(expectedCode);
    }

    private String resultSetToString(Set<Point> points) {
        final int paperWidth = points.stream().map(Point::x).max(comparingInt(Integer::intValue)).get();
        final int paperHeight = points.stream().map(Point::y).max(comparingInt(Integer::intValue)).get();

        final List<String> resultLines = new ArrayList<>();

        for (int y = 0; y <= paperHeight; y++) {
            final StringBuilder line = new StringBuilder();
            for (int x = 0; x <= paperWidth; x++) {
                if (points.contains(new Point(x, y))) {
                    line.append('#');
                } else {
                    line.append('.');
                }
            }
            resultLines.add(line.toString());
        }
        return String.join("\n", resultLines);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day13/test.in", 17),
                Arguments.of("src/test/resources/day13/input1.in", 763)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        final String O = String.join("\n", readFileLines("src/test/resources/day13/codes/O.out"));
        final String RHALRCRA = String.join("\n", readFileLines("src/test/resources/day13/codes/RHALRCRA.out"));
        return Stream.of(
                Arguments.of("src/test/resources/day13/test.in", O),
                Arguments.of("src/test/resources/day13/input1.in", RHALRCRA)
        );
    }
}