package com.advent.of.code.day2;

import java.util.List;

class CommandProcessor {
    Position process(Position initialPosition, List<Command> commands) {
        Position position = initialPosition;
        for (Command command : commands) {
            position = command.calculateNewPosition(position);
        }
        return position;
    }
}
