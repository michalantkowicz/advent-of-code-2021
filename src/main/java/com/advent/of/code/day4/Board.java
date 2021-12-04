package com.advent.of.code.day4;

interface Board {
    void markNumber(int number);
    boolean doesWin();
    int calculateScore(int lastNumber);
}
