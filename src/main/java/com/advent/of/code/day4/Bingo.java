package com.advent.of.code.day4;

import java.util.List;
import java.util.stream.Collectors;

public class Bingo {
    int play(List<Integer> numbers, List<Board> boards) {
        for (int number : numbers) {
            for (Board board : boards) {
                board.markNumber(number);
                if (board.doesWin()) {
                    return board.calculateScore(number);
                }
            }
        }
        throw new UnsupportedOperationException("At least one board should win");
    }

    int playAndFail(List<Integer> numbers, List<Board> boards) {
        final int[] lastWinnerScore = {-1};
        for (int number : numbers) {
            boards = boards.stream().filter(board -> {
                board.markNumber(number);
                if (board.doesWin()) {
                    lastWinnerScore[0] = board.calculateScore(number);
                    return false;
                }
                return true;
            }).collect(Collectors.toList());
        }

        if (lastWinnerScore[0] < 0) {
            throw new UnsupportedOperationException("At least one board should win once");
        } else {
            return lastWinnerScore[0];
        }
    }
}
