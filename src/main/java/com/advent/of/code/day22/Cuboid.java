package com.advent.of.code.day22;

record Cuboid(long minx, long maxx, long miny, long maxy, long minz, long maxz) {
    long volume() {
        return (maxx - minx + 1) * (maxy - miny + 1) * (maxz - minz + 1);
    }

    boolean intersectsWith(Cuboid other) {
        Cuboid overlap = intersectionWith(other);
        return overlap.minx <= overlap.maxx && overlap.miny <= overlap.maxy() && overlap.minz() <= overlap.maxz();
    }

    Cuboid intersectionWith(Cuboid other) {
        return new Cuboid(
                Math.max(minx, other.minx),
                Math.min(maxx, other.maxx),
                Math.max(miny, other.miny),
                Math.min(maxy, other.maxy),
                Math.max(minz, other.minz),
                Math.min(maxz, other.maxz)
        );
    }
}
