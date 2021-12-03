package com.advent.of.code.day2;

class CommandMapper3D {
    Command positionFrom(String serializedPosition) {
        final String[] parts = serializedPosition.split(" ");
        final String type = parts[0];
        final int value = Integer.parseInt(parts[1]);

        return switch (type) {
            case "forward" -> new ForwardCommand3D(value);
            case "down" -> new DownCommand3D(value);
            case "up" -> new UpCommand3D(value);
            default -> throw new UnsupportedOperationException("The type " + type + " is not supported");
        };
    }
}
