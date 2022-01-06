package com.advent.of.code.day25;

import static com.advent.of.code.day25.SeaCucumberType.EAST;
import static com.advent.of.code.day25.SeaCucumberType.SOUTH;

class SeaCucumberSimulator {
    public long getStepWithoutMovement(SeaCucumber[][] input) {
        SeaCucumber[][] map = input;
        long stepCount = 0;
        long moveCount;

        do {
            var eastMoveResult = move(EAST, map);
            var southMoveResult = move(SOUTH, eastMoveResult.map);
            map = southMoveResult.map;
            moveCount = eastMoveResult.moveCount + southMoveResult.moveCount;
            stepCount++;
        } while (moveCount != 0);

        return stepCount;
    }

    private MovementResult move(SeaCucumberType type, SeaCucumber[][] map) {
        final int width = map.length;
        final int height = map[0].length;
        long moveCount = 0;
        final SeaCucumber[][] newMap = new SeaCucumber[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map[x][y] != null && type.equals(map[x][y].type())) {
                    final Point nextPosition = map[x][y].type().calculateNextPosition(new Point(x, y), new Point(width, height));
                    if (map[nextPosition.x()][nextPosition.y()] == null) {
                        newMap[nextPosition.x()][nextPosition.y()] = map[x][y];
                        newMap[x][y] = null;
                        moveCount++;
                    } else {
                        newMap[x][y] = map[x][y];
                    }
                } else if (newMap[x][y] == null) {
                    newMap[x][y] = map[x][y];
                }
            }
        }
        return new MovementResult(newMap, moveCount);
    }

    private static record MovementResult(SeaCucumber[][] map, long moveCount) {
    }
}
