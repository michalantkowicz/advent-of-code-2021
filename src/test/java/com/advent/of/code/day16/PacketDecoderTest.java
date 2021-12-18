package com.advent.of.code.day16;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static org.junit.jupiter.api.Assertions.*;

class PacketDecoderTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldDecodePacketsAndSumAllVersions(String inputFilePath, int expectedValue) {
        // given
        final PacketDecoder decoder = new PacketDecoder();
        final String input = readFileLines(inputFilePath).get(0);

        // when
        final long result = decoder.getSumOfVersions(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldDecodePacketsAndCalculateTheirValues(String inputFilePath, long expectedValue) {
        // given
        final PacketDecoder decoder = new PacketDecoder();
        final String input = readFileLines(inputFilePath).get(0);

        // when
        final long result = decoder.getResult(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day16/test0.in", 14),
                Arguments.of("src/test/resources/day16/test1.in", 16),
                Arguments.of("src/test/resources/day16/test2.in", 12),
                Arguments.of("src/test/resources/day16/test3.in", 23),
                Arguments.of("src/test/resources/day16/test4.in", 31),
                Arguments.of("src/test/resources/day16/input1.in", 1038)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day16/test5.in", 3),
                Arguments.of("src/test/resources/day16/test6.in", 54),
                Arguments.of("src/test/resources/day16/test7.in", 7),
                Arguments.of("src/test/resources/day16/test8.in", 9),
                Arguments.of("src/test/resources/day16/test9.in", 1),
                Arguments.of("src/test/resources/day16/test10.in", 0),
                Arguments.of("src/test/resources/day16/test11.in", 0),
                Arguments.of("src/test/resources/day16/test12.in", 1),
                Arguments.of("src/test/resources/day16/input1.in", 246761930504L)
        );

        //1948794497 low
        //173747486337 low
        //173747486484 low
        //
    }
}