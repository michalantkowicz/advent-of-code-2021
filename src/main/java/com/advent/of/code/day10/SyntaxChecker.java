package com.advent.of.code.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

class SyntaxChecker {
    int countScoreOfCorruptedLines(List<String> lines) {
        final Map<Character, Character> openCloseCharacters = Map.of('(', ')', '{', '}', '[', ']', '<', '>');
        final Map<Character, Character> closeOpenCharacters = openCloseCharacters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        final Map<Character, Integer> scores = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);

        int syntaxErrorScore = 0;

        for (String line : lines) {
            final Stack<Character> stack = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                final char c = line.charAt(i);
                if (openCloseCharacters.containsKey(c)) {
                    stack.push(c);
                } else if (stack.peek().equals(closeOpenCharacters.get(c))) {
                    stack.pop();
                } else {
                    syntaxErrorScore += scores.get(c);
                    break;
                }
            }
        }

        return syntaxErrorScore;
    }

    long getMiddleScore(List<String> lines) {
        final Map<Character, Character> openCloseCharacters = Map.of('(', ')', '{', '}', '[', ']', '<', '>');
        final Map<Character, Character> closeOpenCharacters = openCloseCharacters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        final Map<Character, Integer> scores = Map.of(')', 1, ']', 2, '}', 3, '>', 4);

        List<Long> completionStringScores = new ArrayList<>();

        for (String line : lines) {
            boolean isCorrupted = false;
            final Stack<Character> stack = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                final char c = line.charAt(i);
                if (openCloseCharacters.containsKey(c)) {
                    stack.push(c);
                } else if (stack.peek().equals(closeOpenCharacters.get(c))) {
                    stack.pop();
                } else {
                    isCorrupted = true;
                    break;
                }
            }
            if (!isCorrupted) {
                long completionStringScore = 0;
                while (!stack.isEmpty()) {
                    char requiredCloseCharacter = openCloseCharacters.get(stack.pop());
                    completionStringScore = (completionStringScore * 5) + scores.get(requiredCloseCharacter);
                }
                completionStringScores.add(completionStringScore);
            }
        }

        Collections.sort(completionStringScores);
        return completionStringScores.get(completionStringScores.size() / 2);
    }
}
