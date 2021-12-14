package com.advent.of.code.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PolymerFactory {
    private final Map<Integer, Map<String, Map<Character, Long>>> cache = new HashMap<>();
    private final List<Rule> rules;
    private final int maxStep;

    public PolymerFactory(List<Rule> rules, int maxStep) {
        this.rules = rules;
        this.maxStep = maxStep;
    }

    Map<Character, Long> countPolymerCharacters(String template) {
        final Map<Character, Long> result = new HashMap<>();
        for (int i = 0; i < template.length() - 1; i++) {
            final Map<Character, Long> stepResult = checkPair(template.charAt(i), template.charAt(i + 1), 0);
            add(result, stepResult);
        }
        result.compute(template.charAt(template.length() - 1), (k, v) -> (v == null) ? 1 : v + 1);
        return result;
    }

    private Map<Character, Long> checkPair(char left, char right, int currentStep) {
        if (currentStep < maxStep) {
            final String pairString = "" + left + right;
            if (cache.containsKey(currentStep) && cache.get(currentStep).containsKey(pairString)) {
                return cache.get(currentStep).get(pairString);
            } else {
                for (Rule rule : rules) {
                    if (rule.isValid(left, right)) {
                        final Map<Character, Long> result = new HashMap<>();
                        result.compute(rule.result(), (k, v) -> (v == null) ? 1 : v + 1);

                        final Map<Character, Long> leftResult = checkPair(left, rule.result(), currentStep + 1);
                        add(result, leftResult);

                        final Map<Character, Long> rightResult = checkPair(rule.result(), right, currentStep + 1);
                        add(result, rightResult);

                        updateCache(currentStep, pairString, result);
                        return result;
                    }
                }
            }
        }
        return new HashMap<>();
    }

    private void updateCache(int currentStep, String pairString, Map<Character, Long> result) {
        if (!cache.containsKey(currentStep)) {
            final Map<String, Map<Character, Long>> newEntry = new HashMap<>();
            newEntry.put(pairString, result);
            cache.put(currentStep, newEntry);
        } else if (!cache.get(currentStep).containsKey(pairString)) {
            cache.get(currentStep).put(pairString, result);
        }
    }

    private void add(Map<Character, Long> to, Map<Character, Long> from) {
        from.forEach((k, v) -> to.merge(k, v, Long::sum));
    }
}
