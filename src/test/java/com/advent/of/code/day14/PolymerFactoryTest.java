package com.advent.of.code.day14;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static java.util.Comparator.comparingLong;

class PolymerFactoryTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperSubtractingResultOfMostAndLeastCommonCharAfter10Steps(String inputFilePath, long expectedValue) {
        // given
        final List<String> inputLines = readFileLines(inputFilePath);

        final String inputTemplate = inputLines.get(0);
        final List<Rule> inputRules = inputLines.stream()
                .skip(2)
                .map(line -> new Rule(line.charAt(0), line.charAt(1), line.charAt(line.length() - 1)))
                .collect(Collectors.toList());
        
        final PolymerFactory factory = new PolymerFactory(inputRules, 10);

        // when
        final Map<Character, Long> charactersByCount = factory.countPolymerCharacters(inputTemplate);
        final long mostCommonCount = charactersByCount.values().stream().max(comparingLong(Long::longValue)).get();
        final long leastCommonCount = charactersByCount.values().stream().min(comparingLong(Long::longValue)).get();
        final long result = mostCommonCount - leastCommonCount;

        System.out.println(mostCommonCount);
        System.out.println(leastCommonCount);
        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperSubtractingResultOfMostAndLeastCommonCharAfter40Steps(String inputFilePath, long expectedValue) {
        // given
        final List<String> inputLines = readFileLines(inputFilePath);

        final String inputTemplate = inputLines.get(0);
        final List<Rule> inputRules = inputLines.stream()
                .skip(2)
                .map(line -> new Rule(line.charAt(0), line.charAt(1), line.charAt(line.length() - 1)))
                .collect(Collectors.toList());

        final PolymerFactory factory = new PolymerFactory(inputRules, 40);

        // when
        final Map<Character, Long> charactersByCount = factory.countPolymerCharacters(inputTemplate);
        final long mostCommonCount = charactersByCount.values().stream().max(comparingLong(Long::longValue)).get();
        final long leastCommonCount = charactersByCount.values().stream().min(comparingLong(Long::longValue)).get();
        final long result = mostCommonCount - leastCommonCount;

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day14/test.in", 1588L),
                Arguments.of("src/test/resources/day14/input1.in", 3342L)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day14/test.in", 2188189693529L),
                Arguments.of("src/test/resources/day14/input1.in", 3776553567525L)
        );
    }
}