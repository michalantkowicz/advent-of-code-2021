package com.advent.of.code.day19;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.advent.of.code.day19.PointTransformation.coordinateTransformations;
import static java.lang.Math.abs;

class BeaconScanner {
    int calculateAllPointsCount(List<ScannerArea> areas) {
        final ScannerArea[] rotatedAreas = new ScannerArea[areas.size()];
        rotatedAreas[0] = areas.get(0);
        areas.set(0, null);
        final Point[] areasPositions = new Point[areas.size()];
        areasPositions[0] = new Point(0, 0, 0);
        updateAreas(areas, rotatedAreas, areasPositions);

        final Set<Point> allPoints = new HashSet<>();
        for (int i = 0; i < rotatedAreas.length; i++) {
            final ScannerArea area = rotatedAreas[i];
            for (Point point : area.points()) {
                allPoints.add(point.add(areasPositions[i]));
            }
        }

        return allPoints.size();
    }

    int getMaxDistanceBetweenScanners(List<ScannerArea> areas) {
        final ScannerArea[] rotatedAreas = new ScannerArea[areas.size()];
        rotatedAreas[0] = areas.get(0);
        areas.set(0, null);
        final Point[] areasPositions = new Point[areas.size()];
        areasPositions[0] = new Point(0, 0, 0);
        updateAreas(areas, rotatedAreas, areasPositions);

        int maxDistance = Integer.MIN_VALUE;

        for(int i = 0; i< areasPositions.length; i++) {
            for(int j =0; j < areasPositions.length; j++) {
                if(i != j) {
                    final int currentDistance = getManhattanDistance(areasPositions[i], areasPositions[j]);
                    if(currentDistance > maxDistance) {
                        maxDistance = currentDistance;
                    }
                }
            }
        }

        return maxDistance;
    }

    private void updateAreas(List<ScannerArea> areas, ScannerArea[] rotatedAreas, Point[] areasPositions) {
        while (containsAnyNonNull(areas)) {
            for (int i = 0; i < areas.size(); i++) {
                Integer indexToRemove = null;
                final ScannerArea area = areas.get(i);

                if (area == null) {
                    continue;
                }

                for (PointTransformation transformation : coordinateTransformations) {
                    final ScannerArea transformed = new ScannerArea(
                            area.points().stream().map(transformation::transform).collect(Collectors.toList())
                    );
                    for (int j = 0; j < rotatedAreas.length; j++) {
                        if (rotatedAreas[j] != null) {
                            final List<Tuple<Point, Point>> currentOverlaps = rotatedAreas[j].overlappingPoints(transformed);
                            if (currentOverlaps.size() >= 12) {
                                rotatedAreas[i] = transformed;
                                areasPositions[i] = currentOverlaps.get(0).first().sub(currentOverlaps.get(0).second()).add(areasPositions[j]);
                                indexToRemove = i;
                                break;
                            }
                        }
                    }
                    if (indexToRemove != null) {
                        break;
                    }
                }
                if (indexToRemove != null) {
                    areas.set(indexToRemove, null);
                }
            }
        }
    }

    private int getManhattanDistance(Point a, Point b) {
        final Point result = a.sub(b);
        return abs(result.a()) + abs(result.b()) + abs(result.c());
    }

    private boolean containsAnyNonNull(List<?> list) {
        return list.stream().anyMatch(Objects::nonNull);
    }
}