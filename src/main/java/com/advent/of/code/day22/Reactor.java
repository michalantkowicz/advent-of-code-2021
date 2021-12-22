package com.advent.of.code.day22;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Reactor {
    int reboot(List<Rule> rules) {
        Set<Range> on = new HashSet<>();

        for (Rule rule : rules) {
            if (rule.on()) {
                on.add(rule.range());
            } else {
                final Set<Range> newOn = new HashSet<>();
                for (Range range : on) {
                    newOn.addAll(range.sub(rule.range()));
                }
                on = newOn;
            }
        }

        Set<Point> points = new HashSet<>();

        for (Range range : on) {
            for (long x = range.minx(); x <= range.maxx(); x++) {
                for (long y = range.miny(); y <= range.maxy(); y++) {
                    for (long z = range.minz(); z <= range.maxz(); z++) {
                        points.add(new Point(x, y, z));
                    }
                }
            }
        }

        return points.size();
    }
}
