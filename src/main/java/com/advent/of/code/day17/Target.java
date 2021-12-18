package com.advent.of.code.day17;

record Target(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
    boolean doesContain(Probe probe) {
        return probe.x() >= bottomLeftX && probe.x() <= topRightX && probe.y() >= bottomLeftY && probe.y() <= topRightY;
    }

    boolean hasOvershot(Probe probe) {
        return probe.x() > topRightX || probe.y() < bottomLeftY;
    }
}
