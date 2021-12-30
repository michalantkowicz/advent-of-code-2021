package com.advent.of.code.day21;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

class DiracDiceGame {
    private final Map<Params<Integer, String, Tuple<Integer, Integer>, Tuple<Integer, Integer>, Integer, Boolean>, Tuple<Long, Long>> cache = new HashMap<>();

    long playPracticeGame(int playerOnePosition, int playerTwoPosition) {
        final DeterministicDice dice = new DeterministicDice();
        final GameResult result = play(dice, new GameResult(playerOnePosition, playerTwoPosition), 1000);
        return min(result.getPlayerOneScore(), result.getPlayerTwoScore()) * dice.getRollCount();
    }

    long playRealGame(int playerOnePosition, int playerTwoPosition) {
        final int endResult = 21;
        final Tuple<Long, Long> result1 = playAndSplit(1, "1", new Tuple<>(playerOnePosition, 0), new Tuple<>(playerTwoPosition, 0), endResult, true);
        final Tuple<Long, Long> result2 = playAndSplit(2, "2", new Tuple<>(playerOnePosition, 0), new Tuple<>(playerTwoPosition, 0), endResult, true);
        final Tuple<Long, Long> result3 = playAndSplit(3, "3", new Tuple<>(playerOnePosition, 0), new Tuple<>(playerTwoPosition, 0), endResult, true);
        final Tuple<Long, Long> result = new Tuple<>(
                result1.first() + result2.first() + result3.first(),
                result1.second() + result2.second() + result3.second()
        );

        return max(result.first(), result.second());
    }

    private Tuple<Long, Long> playAndSplit(int roll, String diceRolls, Tuple<Integer, Integer> firstPlayer, Tuple<Integer, Integer> secondPlayer, int endResult, boolean playerOneTurn) {
        if (playerOneTurn) {
            firstPlayer = new Tuple<>(updatePosition(firstPlayer.first(), roll), firstPlayer.second());
            if (diceRolls.length() < 3) {
                final var result1 = getFromCacheOrCalculate(1, diceRolls + "1", firstPlayer, secondPlayer, endResult, true);
                final var result2 = getFromCacheOrCalculate(2, diceRolls + "2", firstPlayer, secondPlayer, endResult, true);
                final var result3 = getFromCacheOrCalculate(3, diceRolls + "3", firstPlayer, secondPlayer, endResult, true);
                return new Tuple<>(
                        result1.first() + result2.first() + result3.first(),
                        result1.second() + result2.second() + result3.second()
                );
            } else {
                firstPlayer = new Tuple<>(firstPlayer.first(), firstPlayer.second() + firstPlayer.first());
                if (firstPlayer.second() >= endResult) {
                    return new Tuple<>(1L, 0L);
                } else {
                    final var result1 = getFromCacheOrCalculate(1, "1", firstPlayer, secondPlayer, endResult, false);
                    final var result2 = getFromCacheOrCalculate(2, "2", firstPlayer, secondPlayer, endResult, false);
                    final var result3 = getFromCacheOrCalculate(3, "3", firstPlayer, secondPlayer, endResult, false);
                    return new Tuple<>(
                            result1.first() + result2.first() + result3.first(),
                            result1.second() + result2.second() + result3.second()
                    );
                }
            }
        } else {
            secondPlayer = new Tuple<>(updatePosition(secondPlayer.first(), roll), secondPlayer.second());
            if (diceRolls.length() < 3) {
                final var result1 = getFromCacheOrCalculate(1, diceRolls + "1", firstPlayer, secondPlayer, endResult, false);
                final var result2 = getFromCacheOrCalculate(2, diceRolls + "2", firstPlayer, secondPlayer, endResult, false);
                final var result3 = getFromCacheOrCalculate(3, diceRolls + "3", firstPlayer, secondPlayer, endResult, false);
                return new Tuple<>(
                        result1.first() + result2.first() + result3.first(),
                        result1.second() + result2.second() + result3.second()
                );
            } else {
                secondPlayer = new Tuple<>(secondPlayer.first(), secondPlayer.second() + secondPlayer.first());
                if (secondPlayer.second() >= endResult) {
                    return new Tuple<>(0L, 1L);
                } else {
                    final var result1 = getFromCacheOrCalculate(1, "1", firstPlayer, secondPlayer, endResult, true);
                    final var result2 = getFromCacheOrCalculate(2, "2", firstPlayer, secondPlayer, endResult, true);
                    final var result3 = getFromCacheOrCalculate(3, "3", firstPlayer, secondPlayer, endResult, true);
                    return new Tuple<>(
                            result1.first() + result2.first() + result3.first(),
                            result1.second() + result2.second() + result3.second()
                    );
                }
            }
        }
    }

    private Tuple<Long, Long> getFromCacheOrCalculate(int roll, String diceRolls, Tuple<Integer, Integer> firstPlayer, Tuple<Integer, Integer> secondPlayer, int endResult, boolean playerOneTurn) {
        final var key = new Params<>(roll, diceRolls, firstPlayer, secondPlayer, endResult, playerOneTurn);
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            var result = playAndSplit(roll, diceRolls, firstPlayer, secondPlayer, endResult, playerOneTurn);
            cache.put(key, result);
            return result;
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
