package com.advent.of.code.day22;

import java.util.ArrayList;
import java.util.List;

class Reactor {
    long reboot(List<Rule> rules) {
        final List<Wrapper> wrappers = new ArrayList<>();

        for (Rule rule : rules) {
            final List<Wrapper> toAdd = new ArrayList<>();
            for (Wrapper wrapper : wrappers) {
                if (wrapper.cuboid().intersectsWith(rule.cuboid())) {
                    toAdd.add(new Wrapper(wrapper.cuboid().intersectionWith(rule.cuboid()), !wrapper.add()));
                }
            }
            
            wrappers.addAll(toAdd);

            if (rule.on()) {
                wrappers.add(new Wrapper(rule.cuboid(), true));
            }
        }

        long result = 0L;

        for (Wrapper wrapper : wrappers) {
            long volume = wrapper.cuboid().volume();
            if (wrapper.add()) {
                result += volume;
            } else {
                result -= volume;
            }
        }

        return result;
    }
}
