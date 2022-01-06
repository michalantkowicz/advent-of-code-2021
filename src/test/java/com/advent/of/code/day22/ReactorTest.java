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
                            final Cuboid cuboid = new Cuboid(
                                    parseLong(x[0]), parseLong(x[1]),
                                    parseLong(y[0]), parseLong(y[1]),
                                    parseLong(z[0]), parseLong(z[1])
                            );
                            return new Rule(cuboid, on);
                        }
                ).collect(Collectors.toList());

        // when
        final long result = reactor.reboot(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSecondTask")
    void test_shouldCalculateProperCountOfActiveCubesForWideRange(String inputFilePath, long expectedValue) {
        // given
        final Reactor reactor = new Reactor();
        final List<Rule> input = readFileLines(inputFilePath).stream()
                .map(line -> {
                            final boolean on = line.startsWith("on");
                            final String[] splits = line.split(",");
                            final String[] x = splits[0].split("=")[1].split("\\.\\.");
                            final String[] y = splits[1].split("=")[1].split("\\.\\.");
                            final String[] z = splits[2].split("=")[1].split("\\.\\.");
                            final Cuboid cuboid = new Cuboid(
                                    parseLong(x[0]), parseLong(x[1]),
                                    parseLong(y[0]), parseLong(y[1]),
                                    parseLong(z[0]), parseLong(z[1])
                            );
                            return new Rule(cuboid, on);
                        }
                ).collect(Collectors.toList());

        // when
        final long result = reactor.reboot(input);

        // then
        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideArgumentsForFirstTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day22/test0.in", 26L),
                Arguments.of("src/test/resources/day22/test1.in", 39L),
                Arguments.of("src/test/resources/day22/test2.in", 590784L),
                Arguments.of("src/test/resources/day22/input1.in", 607657L)
        );
    }

    private static Stream<Arguments> provideArgumentsForSecondTask() {
        return Stream.of(
                Arguments.of("src/test/resources/day22/test3.in", 2758514936282235L),
                Arguments.of("src/test/resources/day22/input2.in", 1187742789778677L)
        );
    }
}