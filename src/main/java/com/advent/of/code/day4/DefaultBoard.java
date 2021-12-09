package com.advent.of.code.day4;

import java.util.List;

public class DefaultBoard implements Board {
    private static record BoardNumber(int value, boolean isMarked) {
    }

    @FunctionalInterface
    private interface QuadFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }

    private int width, height;
    private BoardNumber boardNumbers[][];

    DefaultBoard(List<List<Integer>> numbers) {
        width = numbers.get(0).size();
        height = numbers.size();

        this.boardNumbers = new BoardNumber[width][height];

        traverseBoard((boardNumber, x, y) -> new BoardNumber(numbers.get(x).get(y), false));
    }

    @Override
    public void markNumber(int number) {
        traverseBoard((boardNumber, x, y) -> new BoardNumber(boardNumber.value, boardNumber.isMarked || boardNumber.value == number));
    }

    @Override
    public boolean doesWin() {
        // check columns
        for (int i = 0; i < width; i++) {
            int columnMarkedCount = 0;
            for (int j = 0; j < height; j++) {
                if (boardNumbers[j][i].isMarked) {
                    columnMarkedCount++;
                }
            }
            if (columnMarkedCount == height) {
                return true;
            }
        }
        // check rows
        for (int j = 0; j < height; j++) {
            int rowMarkedCount = 0;
            for (int i = 0; i < width; i++) {
                if (boardNumbers[j][i].isMarked) {
                    rowMarkedCount++;
                }
            }
            if (rowMarkedCount == width) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int calculateScore(int lastNumber) {
        final int[] result = {0};
        traverseBoard((boardNumber, x, y) -> {
            result[0] += (boardNumber.isMarked) ? 0 : boardNumber.value;
            return boardNumber;
        });
        return result[0] * lastNumber;
    }

    private void traverseBoard(QuadFunction<BoardNumber, Integer, Integer, BoardNumber> transform) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardNumbers[j][i] = transform.apply(boardNumbers[j][i], j, i);
            }
        }
    }
}
