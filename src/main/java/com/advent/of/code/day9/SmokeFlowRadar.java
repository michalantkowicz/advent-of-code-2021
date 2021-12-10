package com.advent.of.code.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class SmokeFlowRadar {
    int getSumOfRiskLevels(Location[][] heightmap) {
        int result = 0;
        for (Location[] locations : heightmap) {
            for (Location location : locations) {
                if (isNeighbourHigher(heightmap, location.getHeight(), location.getPosition().left())
                        && isNeighbourHigher(heightmap, location.getHeight(), location.getPosition().right())
                        && isNeighbourHigher(heightmap, location.getHeight(), location.getPosition().down())
                        && isNeighbourHigher(heightmap, location.getHeight(), location.getPosition().up())
                ) {
                    result += location.getHeight() + 1;
                }
            }
        }
        return result;
    }

    private boolean isNeighbourHigher(Location[][] heightMap, int locationHeight, Position neighbourPosition) {
        return !(areValid(heightMap, neighbourPosition)) || heightMap[neighbourPosition.x()][neighbourPosition.y()].getHeight() > locationHeight;
    }

    int getResultOfThreeBiggestBasins(Location[][] heightmap) {
        calculateBasins(heightmap);

        final List<Integer> basinSizes = new ArrayList<>();
        for (Location[] locations : heightmap) {
            for (Location location : locations) {
                final int basinSize = location.getBasinSize();
                if (basinSize > 0) {
                    basinSizes.add(basinSize);
                }
            }
        }

        return basinSizes.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(1, (a, b) -> a * b);
    }

    private void calculateBasins(Location[][] heightmap) {
        for (Location[] locations : heightmap) {
            for (Location location : locations) {
                if (location.getHeight() != 9) {
                    goAndIncrementTheDeepestPoint(heightmap, location);
                }
            }
        }
    }

    private void goAndIncrementTheDeepestPoint(Location[][] heightmap, Location location) {
        final Location deepestNeighbour = Stream.of(
                        getDeeperNeighbourOrLocation(heightmap, location, location.getPosition().left()),
                        getDeeperNeighbourOrLocation(heightmap, location, location.getPosition().right()),
                        getDeeperNeighbourOrLocation(heightmap, location, location.getPosition().down()),
                        getDeeperNeighbourOrLocation(heightmap, location, location.getPosition().up())
                )
                .min(Comparator.comparingInt(Location::getHeight))
                .get();

        if (deepestNeighbour.getHeight() < location.getHeight()) {
            goAndIncrementTheDeepestPoint(heightmap, deepestNeighbour);
        } else {
            location.incrementBasinSize();
        }
    }

    private Location getDeeperNeighbourOrLocation(Location[][] heightmap, Location location, Position neighbourPosition) {
        return areValid(heightmap, neighbourPosition) ? heightmap[neighbourPosition.x()][neighbourPosition.y()] : location;
    }

    private boolean areValid(Location[][] heightMap, Position position) {
        return position.x() >= 0 && position.x() < heightMap.length && position.y() >= 0 && position.y() < heightMap[0].length;
    }
}
