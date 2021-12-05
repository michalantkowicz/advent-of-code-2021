package com.advent.of.code.day5;

import java.util.List;

class VentsRadar {
    long getIntersectionsOf(List<Segment> segments, int atLeast) {
        int xmin = segments.stream().mapToInt(Segment::getMinX).min().getAsInt();
        int ymin = segments.stream().mapToInt(Segment::getMinY).min().getAsInt();
        int xmax = segments.stream().mapToInt(Segment::getMaxX).max().getAsInt();
        int ymax = segments.stream().mapToInt(Segment::getMaxY).max().getAsInt();

        long result = 0L;

        for (int i = xmin; i <= xmax; i++) {
            for (int j = ymin; j <= ymax; j++) {
                long intersections = 0;
                for (Segment segment : segments) {
                    if (segment.doesContainPoint(i, j)) {
                        intersections++;
                    }
                }
                if (intersections >= atLeast) {
                    result++;
                }
            }
        }
        return result;
    }
}
