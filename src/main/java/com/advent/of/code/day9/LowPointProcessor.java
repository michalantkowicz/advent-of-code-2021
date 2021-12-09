package com.advent.of.code.day9;

public class LowPointProcessor {
    int getSumOfRiskLevels(int[][] heightmap) {
        int result = 0;
        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                int locationHeight = heightmap[i][j];
                if (testNeighbour(heightmap, i - 1, j, locationHeight)
                        && testNeighbour(heightmap, i + 1, j, locationHeight)
                        && testNeighbour(heightmap, i, j - 1, locationHeight)
                        && testNeighbour(heightmap, i, j + 1, locationHeight)
                ) {
                    result += locationHeight + 1;
                }
            }
        }
        return result;
    }

    private boolean testNeighbour(int[][] heightMap, int x, int y, int locationHeight) {
        return x < 0 || x >= heightMap.length || y < 0 || y >= heightMap[0].length || heightMap[x][y] > locationHeight;
    }
}
