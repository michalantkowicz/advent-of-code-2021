package com.advent.of.code.day16;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

class HexDecoder {
    private static final Map<Character, String> hexToBin = Map.ofEntries(
            new SimpleEntry<>('0', "0000"),
            new SimpleEntry<>('1', "0001"),
            new SimpleEntry<>('2', "0010"),
            new SimpleEntry<>('3', "0011"),
            new SimpleEntry<>('4', "0100"),
            new SimpleEntry<>('5', "0101"),
            new SimpleEntry<>('6', "0110"),
            new SimpleEntry<>('7', "0111"),
            new SimpleEntry<>('8', "1000"),
            new SimpleEntry<>('9', "1001"),
            new SimpleEntry<>('A', "1010"),
            new SimpleEntry<>('B', "1011"),
            new SimpleEntry<>('C', "1100"),
            new SimpleEntry<>('D', "1101"),
            new SimpleEntry<>('E', "1110"),
            new SimpleEntry<>('F', "1111")
    );

    String decode(String hex) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            result.append(hexToBin.get(hex.charAt(i)));
        }
        return result.toString();
    }
}
