package com.advent.of.code.day20;

class ImageProcessor {
    private static int lastEmptySign = 0;

    int processTwiceAndCountLightPixels(int[][] image, String outputRule, int times) {
        int[][] processed = image;

        for(int i = 0; i < times; i++) {
            processed = process(processed, outputRule);
        }

        int lightCount = 0;
        for (int i = 0; i < processed.length; i++) {
            for (int j = 0; j < processed[0].length; j++) {
                lightCount += processed[i][j];
            }
        }
        return lightCount;
    }

    private int[][] process(int[][] image, String outputRule) {
        int[][] result = new int[image.length + 2][image[0].length + 2];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                int x = i - 1;
                int y = j - 1;
                if (isValid(image, x, y)) {
                    result[i][j] = image[x][y];
                } else {
                    result[i][j] = lastEmptySign;
                }
            }
        }

        int[][] newResult = new int[result.length][result[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                newResult[i][j] = processByRule(outputRule, result, i, j);
            }
        }

        final int[][] arrayOfEmpties = new int[][] {
                {lastEmptySign, lastEmptySign, lastEmptySign},
                {lastEmptySign, lastEmptySign, lastEmptySign},
                {lastEmptySign, lastEmptySign, lastEmptySign}
        };
        lastEmptySign = processByRule(outputRule, arrayOfEmpties, 1, 1);

        return newResult;
    }

    private int processByRule(String rule, int[][] a, int x, int y) {
        final String binaryNumber = getBinaryNumber(a, x, y);
        final int index = fromBinary(binaryNumber);
        return Integer.parseInt(rule.charAt(index) + "");
    }

    private int fromBinary(String binary) {
        return Integer.parseInt(binary, 2);
    }

    private String getBinaryNumber(int[][] image, int x, int y) {
        final StringBuilder result = new StringBuilder();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isValid(image, i, j)) {
                    result.append(image[i][j]);
                } else {
                    result.append(lastEmptySign);
                }
            }
        }
        return result.toString();
    }

    private boolean isValid(int[][] a, int x, int y) {
        return x >= 0 && y >= 0 && x < a.length && y < a[0].length;
    }
}
