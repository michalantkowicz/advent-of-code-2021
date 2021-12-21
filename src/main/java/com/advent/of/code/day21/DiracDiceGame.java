package com.advent.of.code.day21;

import static java.lang.Math.max;
import static java.lang.Math.min;

class DiracDiceGame {
    long playPracticeGame(int playerOnePosition, int playerTwoPosition) {
        final DeterministicDice dice = new DeterministicDice();
        final GameResult result = play(dice, new GameResult(playerOnePosition, playerTwoPosition), 1000);
        return min(result.getPlayerOneScore(), result.getPlayerTwoScore()) * dice.getRollCount();
    }

    long playRealGame(int playerOnePosition, int playerTwoPosition) {
        final Tuple<Long, Long> result1 = playAndSplit(new DiracDice(1), 1, new GameResult(playerOnePosition, playerTwoPosition), 0, true);
        final Tuple<Long, Long> result2 = playAndSplit(new DiracDice(2), 1, new GameResult(playerOnePosition, playerTwoPosition), 0, true);
        final Tuple<Long, Long> result3 = playAndSplit(new DiracDice(3), 1, new GameResult(playerOnePosition, playerTwoPosition), 0, true);
        final Tuple<Long, Long> result = new Tuple<>(
                result1.first() + result2.first() + result3.first(),
                result1.second() + result2.second() + result3.second()
        );

        System.out.println(c);
        return max(result.first(), result.second());
    }

    static int c = 0;

    private Tuple<Long, Long> playAndSplit(DiracDice dice, int diceRollsCount, GameResult result, int endResult, boolean playerOneTurn) {
        c++;
        if (playerOneTurn) {
            result.setPlayerOnePosition(updatePosition(result.getPlayerOnePosition(), dice.getValue()));
            if (diceRollsCount <= 3) {
                final Tuple<Long, Long> result1 = playAndSplit(new DiracDice(1), diceRollsCount + 1, result, endResult, true);
                final Tuple<Long, Long> result2 = playAndSplit(new DiracDice(2), diceRollsCount + 1, result, endResult, true);
                final Tuple<Long, Long> result3 = playAndSplit(new DiracDice(3), diceRollsCount + 1, result, endResult, true);
                return new Tuple<>(
                        result1.first() + result2.first() + result3.first(),
                        result1.second() + result2.second() + result3.second()
                );
            } else {
                result.setPlayerOneScore(+result.getPlayerOnePosition());
                if (result.getPlayerOneScore() >= endResult) {
                    return new Tuple<>(1L, 0L);
                } else {
                    final Tuple<Long, Long> result1 = playAndSplit(new DiracDice(1), 1, result, endResult, false);
                    final Tuple<Long, Long> result2 = playAndSplit(new DiracDice(2), 1, result, endResult, false);
                    final Tuple<Long, Long> result3 = playAndSplit(new DiracDice(3), 1, result, endResult, false);
                    return new Tuple<>(
                            result1.first() + result2.first() + result3.first(),
                            result1.second() + result2.second() + result3.second()
                    );
                }
            }
        } else {
            result.setPlayerTwoPosition(updatePosition(result.getPlayerTwoPosition(), dice.getValue()));
            if (diceRollsCount <= 3) {
                final Tuple<Long, Long> result1 = playAndSplit(new DiracDice(1), diceRollsCount + 1, result, endResult, false);
                final Tuple<Long, Long> result2 = playAndSplit(new DiracDice(2), diceRollsCount + 1, result, endResult, false);
                final Tuple<Long, Long> result3 = playAndSplit(new DiracDice(3), diceRollsCount + 1, result, endResult, false);
                return new Tuple<>(
                        result1.first() + result2.first() + result3.first(),
                        result1.second() + result2.second() + result3.second()
                );
            } else {
                result.setPlayerTwoScore(result.getPlayerTwoScore() + result.getPlayerTwoPosition());
                if (result.getPlayerTwoScore() >= endResult) {
                    return new Tuple<>(0L, 1L);
                } else {
                    final Tuple<Long, Long> result1 = playAndSplit(new DiracDice(1), 1, result, endResult, true);
                    final Tuple<Long, Long> result2 = playAndSplit(new DiracDice(2), 1, result, endResult, true);
                    final Tuple<Long, Long> result3 = playAndSplit(new DiracDice(3), 1, result, endResult, true);
                    return new Tuple<>(
                            result1.first() + result2.first() + result3.first(),
                            result1.second() + result2.second() + result3.second()
                    );
                }
            }
        }
    }

    private GameResult play(DeterministicDice dice, GameResult result, int endResult) {
        while (result.getPlayerOneScore() < endResult && result.getPlayerTwoScore() < endResult) {
            for (int score : dice.roll()) {
                result.setPlayerOnePosition(updatePosition(result.getPlayerOnePosition(), score));
            }
            result.setPlayerOneScore(result.getPlayerOneScore() + result.getPlayerOnePosition());
            if (result.getPlayerOneScore() < endResult) {
                for (int score : dice.roll()) {
                    result.setPlayerTwoPosition(updatePosition(result.getPlayerTwoPosition(), score));
                }
                result.setPlayerTwoScore(result.getPlayerTwoScore() + result.getPlayerTwoPosition());
            }
        }
        return result;
    }

    private int updatePosition(int position, int score) {
        position = (position + score) % 10;
        position = (position == 0) ? 10 : position;
        return position;
    }
}
