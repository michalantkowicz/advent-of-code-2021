package com.advent.of.code.day18;

import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Integer.parseInt;
import static java.lang.Math.floor;

class SnailfishCalculator {
    int calculateMaxMagnitudeSummingTwoOf(List<String> numbers) {
        int maxMagnitude = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (i != j) {
                    final int magnitude = calculateMagnitude(sum(List.of(numbers.get(i), numbers.get(j))));
                    if (magnitude > maxMagnitude) {
                        maxMagnitude = magnitude;
                    }
                }
            }
        }
        return maxMagnitude;
    }

    int calculateMagnitudeForSumOf(List<String> numbers) {
        return calculateMagnitude(sum(numbers));
    }

    int calculateMagnitude(String number) {
        while (number.contains("[") || number.contains("]")) {
            char lastBracket = '[';
            int lastBracketIndex = 0;
            for (int i = 0; i < number.length(); i++) {
                if (lastBracket == '[' && number.charAt(i) == ']') {
                    final String pair = number.substring(lastBracketIndex, i + 1);
                    final Tuple<Integer, Integer> pairValues = parseValues(pair);
                    number = number.substring(0, lastBracketIndex)
                            + ((3 * pairValues.first()) + (2 * pairValues.second()))
                            + ((i < number.length() - 1) ? number.substring(i + 1) : "");
                    break;
                } else if (number.charAt(i) == '[' || number.charAt(i) == ']') {
                    lastBracket = number.charAt(i);
                    lastBracketIndex = i;
                }
            }
        }
        return parseInt(number);
    }

    String sum(List<String> numbers) {
        String sum = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            sum = "[" + sum + "," + numbers.get(i) + "]";
            sum = reduce(sum);
        }
        return sum;
    }

    private String reduce(String number) {
        while (true) {
            String explodedNumber = explode(number);
            if (explodedNumber.equals(number)) {
                String splittedNumber = split(number);
                if (splittedNumber.equals(number)) {
                    break;
                } else {
                    number = splittedNumber;
                }
            } else {
                number = explodedNumber;
            }
        }
        return number;
    }

    String explode(String number) {
        int openingBrackets = 0;
        for (int i = 0; i < number.length(); i++) {
            if (openingBrackets == 5) {
                int pairIndex = i - 1;
                String pair = "";
                for (int j = pairIndex; number.charAt(j - 1) != ']'; j++) {
                    pair += number.substring(j, j + 1);
                }
                final Tuple<Integer, Integer> pairValues = parseValues(pair);

                int firstLeftValueIndex = -1;
                String firstLeftValue = "";
                for (int j = pairIndex; j >= 0; j--) {
                    if (isDigit(number.charAt(j))) {
                        firstLeftValue += number.charAt(j);
                        firstLeftValueIndex = j;
                        int k = j - 1;
                        while (k >= 0 && isDigit(number.charAt(k))) {
                            firstLeftValue = number.charAt(k) + firstLeftValue;
                            firstLeftValueIndex = k;
                            k--;
                        }
                        break;
                    }
                }

                int firstRightValueIndex = -1;
                String firstRightValue = "";
                for (int j = pairIndex + pair.length(); j < number.length(); j++) {
                    if (isDigit(number.charAt(j))) {
                        firstRightValue += number.charAt(j);
                        firstRightValueIndex = j;
                        int k = j + 1;
                        while (k < number.length() && isDigit(number.charAt(k))) {
                            firstRightValue += number.charAt(k);
                            k++;
                        }
                        break;
                    }
                }

                String result = "";
                if (firstLeftValueIndex > 0) {
                    result += number.substring(0, firstLeftValueIndex)
                            + (parseInt(firstLeftValue) + pairValues.first())
                            + number.substring(firstLeftValueIndex + firstLeftValue.length(), pairIndex);
                } else {
                    result += number.substring(0, pairIndex);
                }

                result += "0";

                if (firstRightValueIndex > 0) {
                    result += number.substring(pairIndex + pair.length(), firstRightValueIndex)
                            + (parseInt(firstRightValue) + pairValues.second())
                            + number.substring(firstRightValueIndex + firstRightValue.length());
                } else {
                    result += number.substring(pairIndex + pair.length());
                }

                return result;
            } else if (number.charAt(i) == '[') {
                openingBrackets++;
            } else if (number.charAt(i) == ']') {
                openingBrackets--;
            }
        }
        return number;
    }

    String split(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (isDigit(number.charAt(i))) {
                String numberToSplit = "" + number.charAt(i);
                int j = i + 1;
                while (j < number.length() && isDigit(number.charAt(j))) {
                    numberToSplit += number.charAt(j);
                    j++;
                }
                final int numberToSplitValue = parseInt(numberToSplit);
                if (numberToSplitValue >= 10) {
                    return number.substring(0, i)
                            + "[" + (int) floor(numberToSplitValue / 2d) + "," + (int) Math.ceil(numberToSplitValue / 2d) + "]"
                            + number.substring(i + numberToSplit.length());
                }
            }
        }
        return number;
    }

    private Tuple<Integer, Integer> parseValues(String pair) {
        final String[] values = pair.substring(1, pair.length() - 1).split(",");
        return new Tuple<>(parseInt(values[0]), parseInt(values[1]));
    }
}