package com.advent.of.code.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

record ScannerArea(List<Point> points) {
    List<Tuple<Point, Point>> overlappingPoints(ScannerArea that) {
        List<Tuple<Point, Point>> result = new ArrayList<>();
        final var thisVectors = this.points.stream().map(point -> this.points.stream().map(p -> new Tuple<>(p, Vector.of(p, point))).collect(Collectors.toList())).collect(Collectors.toList());
        final var thatVectors = that.points.stream().map(point -> that.points.stream().map(p -> new Tuple<>(p, Vector.of(p, point))).collect(Collectors.toList())).collect(Collectors.toList());

        for (List<Tuple<Point, Vector>> thisPairs : thisVectors) {
            for (List<Tuple<Point, Vector>> thatPairs : thatVectors) {
                for(Tuple<Point, Vector> thisPair : thisPairs) {
                    for(Tuple<Point, Vector> thatPair: thatPairs) {
                        if (thisPair.second().equals(thatPair.second())) {
                            result.add(new Tuple<>(thisPair.first(), thatPair.first()));
                        }
                    }
                }
                if(result.size() >= 12) {
                    return result;
                } else {
                    result = new ArrayList<>();
                }
            }
        }
        return result;
    }
}