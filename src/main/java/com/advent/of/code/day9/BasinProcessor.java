package com.advent.of.code.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class BasinProcessor {
    int getResultOfThreeBiggestBasins(Location[][] heightmap) {
        updateBasins(heightmap);

        final List<Integer> basinSizes = new ArrayList<>();
        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                final int basinSize = heightmap[i][j].getBasinSize();
                if (basinSize > 0) {
                    basinSizes.add(basinSize);
                }
            }
        }

        return basinSizes.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(1, (a, b) -> a * b);
    }

    private void updateBasins(Location[][] heightmap) {
        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                if (heightmap[i][j].getHeight() != 9) {
                    goAndIncrementTheDeepestPoint(heightmap, i, j);
                }
            }
        }
    }

    private void goAndIncrementTheDeepestPoint(Location[][] heightmap, int x, int y) {
        final Location location = heightmap[x][y];
        var deepestNeighbour = Stream.of(
                        areValid(heightmap, x - 1, y) ? heightmap[x - 1][y] : location,
                        areValid(heightmap, x + 1, y) ? heightmap[x + 1][y] : location,
                        areValid(heightmap, x, y - 1) ? heightmap[x][y - 1] : location,
                        areValid(heightmap, x, y + 1) ? heightmap[x][y + 1] : location
                )
                .min(Comparator.comparingInt(Location::getHeight))
                .get();

        if (deepestNeighbour.getHeight() < location.getHeight()) {
            goAndIncrementTheDeepestPoint(heightmap, deepestNeighbour.getX(), deepestNeighbour.getY());
        } else {
            location.incrementBasinSize();
        }
    }

    private boolean areValid(Location[][] heightMap, int x, int y) {
        return x >= 0 && x < heightMap.length && y >= 0 && y < heightMap[0].length;
    }
}
