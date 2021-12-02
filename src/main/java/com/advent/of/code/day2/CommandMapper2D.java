package com.advent.of.code.day2;

class CommandMapper2D {
    Command positionFrom(String serializedPosition) {
        final String[] parts = serializedPosition.split(" ");
        final String type = parts[0];
        final int value = Integer.parseInt(parts[1]);

        return switch (type) {
            case "forward" -> new ForwardCommand2D(value);
            case "down" -> new DownCommand2D(value);
            case "up" -> new UpCommand2D(value);
            default -> throw new UnsupportedOperationException("The type " + type + " is not supported");
        };
    }
}
