package com.advent.of.code.day1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.advent.of.code.Utils.readFileLines;


class ReportProcessorTest {
    @Test
    void test_shouldReturnCountOfIncreases() {
        // given
        final ReportProcessor processor = new ReportProcessor();
        final List<Integer> input = readFileLines("src/test/resources/day1/input1.in").stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // when
        int result = processor.countIncreases(input);

        // then
        Assertions.assertThat(result).isEqualTo(1462);
    }

    @Test
    void test_shouldReturnCountOfIncreasesWithSlidingWindow() {
        // given
        final ReportProcessor processor = new ReportProcessor();
        final List<Integer> input = readFileLines("src/test/resources/day1/input2.in").stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // when
        int result = processor.countIncreasesWithSlidingWindow(input, 3);

        // then
        Assertions.assertThat(result).isEqualTo(1497);
    }
}