package com.stellarroutine.map;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Direction from(String directionString) {
        switch (directionString) {
            case "north":
            case "n":
                return Direction.NORTH;
            case "east":
            case "e":
                return Direction.EAST;
            case "south":
            case "s":
                return Direction.SOUTH;
            case "west":
            case "w":
                return Direction.WEST;
            default:
                return null;
        }
    }
}
