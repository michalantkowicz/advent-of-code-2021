package com.advent.of.code.day17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ProbeShooter {
    int simulateAndFindHighestPoint(Target target) {
        final Map<Tuple<Integer, Integer>, Integer> highestPointByInitialVelocity = new HashMap<>();
        for (int vX = 0; vX < 100; vX++) { // max vX, vY is arbitrary - it should be calculated due to target position
            for (int vY = 0; vY < 100; vY++) {
                Probe probe = new Probe(0, 0, vX, vY);
                while (!target.doesContain(probe) && !target.hasOvershot(probe)) {
                    if(highestPointByInitialVelocity.getOrDefault(new Tuple<>(vX, vY), Integer.MIN_VALUE) < probe.y()) {
                        highestPointByInitialVelocity.put(new Tuple<>(vX, vY), probe.y());
                    }
                    probe = simulateStep(probe);
                }
                if(target.hasOvershot(probe)) {
                    highestPointByInitialVelocity.remove(new Tuple<>(vX, vY));
                }
            }
        }
        return highestPointByInitialVelocity.values().stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    int simulateAndFindProperInitialVelocitiesCount(Target target) {
        final Set<Tuple<Integer, Integer>> properInitialVelocities = new HashSet<>();
        for (int vX = -400; vX < 400; vX++) { // max vX, vY is arbitrary - it should be calculated due to target position
            for (int vY = -400; vY < 400; vY++) {
                Probe probe = new Probe(0, 0, vX, vY);
                properInitialVelocities.add(new Tuple<>(vX, vY));
                while (!target.doesContain(probe) && !target.hasOvershot(probe)) {
                    probe = simulateStep(probe);
                }
                if(target.hasOvershot(probe)) {
                    properInitialVelocities.remove(new Tuple<>(vX, vY));
                }
            }
        }
        return properInitialVelocities.size();
    }

    private Probe simulateStep(Probe probe) {
        final int newPositionX = probe.x() + probe.velocityX();
        final int newPositionY = probe.y() + probe.velocityY();
        final int newVelocityX = (int) (probe.velocityX() - (Math.signum(probe.velocityX())));
        final int newVelocityY = probe.velocityY() - 1;
        return new Probe(newPositionX, newPositionY, newVelocityX, newVelocityY);
    }
}