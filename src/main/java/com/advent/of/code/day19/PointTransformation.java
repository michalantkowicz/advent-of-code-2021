package com.advent.of.code.day19;

import java.util.List;

interface PointTransformation {
    Point transform(Point point);

    List<PointTransformation> coordinateTransformations = List.of(
            p -> new Point(p.a(), p.b(), p.c()),
            p -> new Point(-p.b(), p.a(), p.c()),
            p -> new Point(-p.a(), -p.b(), p.c()),
            p -> new Point(p.b(), -p.a(), p.c()),

            p -> new Point(p.c(), p.b(), -p.a()),
            p -> new Point(-p.b(), p.c(), -p.a()),
            p -> new Point(-p.c(), -p.b(), -p.a()),
            p -> new Point(p.b(), -p.c(), -p.a()),

            p -> new Point(-p.c(), p.b(), p.a()),
            p -> new Point(-p.b(), -p.c(), p.a()),
            p -> new Point(p.c(), -p.b(), p.a()),
            p -> new Point(p.b(), p.c(), p.a()),

            p -> new Point(-p.a(), p.b(), -p.c()),
            p -> new Point(-p.b(), -p.a(), -p.c()),
            p -> new Point(p.a(), -p.b(), -p.c()),
            p -> new Point(p.b(), p.a(), -p.c()),

            p -> new Point(p.a(), -p.c(), p.b()),
            p -> new Point(p.c(), p.a(), p.b()),
            p -> new Point(-p.a(), p.c(), p.b()),
            p -> new Point(-p.c(), -p.a(), p.b()),

            p -> new Point(p.a(), p.c(), -p.b()),
            p -> new Point(-p.c(), p.a(), -p.b()),
            p -> new Point(-p.a(), -p.c(), -p.b()),
            p -> new Point(p.c(), -p.a(), -p.b())
    );
}