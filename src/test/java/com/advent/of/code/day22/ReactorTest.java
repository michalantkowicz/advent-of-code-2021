package com.advent.of.code.day22;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.advent.of.code.Utils.readFileLines;
import static java.lang.Long.parseLong;

class ReactorTest {
    @ParameterizedTest
    @MethodSource("provideArgumentsForFirstTask")
    void test_shouldCalculateProperCountOfActiveCubes(String inputFilePath, long expectedValue) {
        // given
        final Reactor reactor = new Reactor();
        final List<Rule> input = readFileLines(inputFilePath).stream()
                .map(line -> {
                            final boolean on = line.startsWith("on");
                            final String[] splits = line.split(",");
                            final String[] x = splits[0].split("=")[1].split("\\.\\.");
                            final String[] y = splits[1].split("=")[1].split("\\.\\.");
                            final String[] z = splits[2].split("=")[1].split("\\.\\.");
                            final Range range = new Range(
                                    parseLong(x[0]), parseLong(x[1]),
                                    parseLong(y[0]), parseLong(y[1]),
                                    parseLong(z[0]), parseLong(z[1])
                            );
                            return new Rule(range, on);
                        }
                ).collect(Collectors.toList());

        // when
        final long result = reactor.reboot(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day22/test1.in", 39L),
                Arguments.of("src/test/resources/day22/test2.in", 590784L)
        );
    }
}