package com.advent.of.code.day22;

import java.util.List;
import java.util.stream.Collectors;

record Range(long minx, long maxx, long miny, long maxy, long minz, long maxz) {
    List<Range> sub(Range other) {
        if (!intersectsWith(other)) {
            return List.of(this);
        } else {
            return Calculation.boundaryCuboids.stream()
                    .map(calculation -> calculation.calculate(this, other))
                    .filter(Range::isValid)
                    .map(this::normalize)
                    .filter(this::intersectsWith)
                    .collect(Collectors.toList());
        }
    }

    boolean intersectsWith(Range other) {
        return (other.minx >= minx && other.minx <= maxx)
                && (other.miny >= miny && other.maxy <= maxy)
                && (other.minz >= minz && other.maxz <= maxz);
    }

    Range normalize(Range other) {
        long resultMinX = Math.max(other.minx, minx);
        long resultMaxX = Math.min(other.maxx, maxx);
        long resultMinY = Math.max(other.miny, miny);
        long resultMaxY = Math.min(other.maxy, maxy);
        long resultMinZ = Math.max(other.minz, minz);
        long resultMaxZ = Math.min(other.maxz, maxz);
        return new Range(resultMinX, resultMaxX, resultMinY, resultMaxY, resultMinZ, resultMaxZ);
    }

    boolean isValid() {
        return (maxx - minx >= 0) && (maxy - miny >= 0) && (maxz - minz >= 0);
    }

    long cubature() {
        return (maxx - minx + 1) * (maxy - miny + 1) * (maxz - minz + 1);
    }
}
