package com.advent.of.code.day20;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;

class ImageProcessorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateCountOfLightPixelsAfter2Times(String inputFilePath, int expectedValue) {
        // given
        final ImageProcessor processor = new ImageProcessor();
        final List<String> inputLines = readFileLines(inputFilePath);
        String outputRule = inputLines.get(0);
        outputRule = outputRule.replaceAll("\\.", "0");
        outputRule = outputRule.replaceAll("#", "1");

        int[][] input = new int[inputLines.size() - 2][inputLines.get(2).length()];
        for (int i = 2; i < inputLines.size(); i++) {
            for (int j = 0; j < inputLines.get(i).length(); j++) {
                input[i - 2][j] = (inputLines.get(i).charAt(j) == '#') ? 1 : 0;
            }
        }

        // when
        final int result = processor.processTwiceAndCountLightPixels(input, outputRule, 2);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateCountOfLightPixelsAfter50Times(String inputFilePath, int expectedValue) {
        // given
        final ImageProcessor processor = new ImageProcessor();
        final List<String> inputLines = readFileLines(inputFilePath);
        String outputRule = inputLines.get(0);
        outputRule = outputRule.replaceAll("\\.", "0");
        outputRule = outputRule.replaceAll("#", "1");

        int[][] input = new int[inputLines.size() - 2][inputLines.get(2).length()];
        for (int i = 2; i < inputLines.size(); i++) {
            for (int j = 0; j < inputLines.get(i).length(); j++) {
                input[i - 2][j] = (inputLines.get(i).charAt(j) == '#') ? 1 : 0;
            }
        }

        // when
        final int result = processor.processTwiceAndCountLightPixels(input, outputRule, 50);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day20/test.in", 35),
                Arguments.of("src/test/resources/day20/input1.in", 5268)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day20/test.in", 3351),
                Arguments.of("src/test/resources/day20/input1.in", 16875)
        );
    }
}