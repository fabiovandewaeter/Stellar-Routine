package com.stellarroutine.core;

import com.stellarroutine.entities.Player;
import com.stellarroutine.map.Direction;
import com.stellarroutine.map.Room;

public class Main {
    public static void main(String[] args) {
        Room defaultRoom = new Room("default");
        Room room2 = new Room("room2");
        Room room3 = new Room("room3");
        defaultRoom.addNeighbor(Direction.NORTH, room2);
        defaultRoom.addNeighbor(Direction.WEST, room3);

        room2.addNeighbor(Direction.SOUTH, defaultRoom);

        room3.addNeighbor(Direction.EAST, defaultRoom);

        Player player = new Player("Bebou", defaultRoom);
        Game game = new Game(player);

        game.run();
    }
}
