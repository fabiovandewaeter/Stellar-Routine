package com.stellarroutine.core;

import com.stellarroutine.entities.Player;
import com.stellarroutine.map.Room;

public class Main {
    public static void main(String[] args) {
        Room defaultRoom = new Room("default");
        Player player = new Player("Bebou", defaultRoom);
        Game game = new Game(player);

        game.run();
    }
}
