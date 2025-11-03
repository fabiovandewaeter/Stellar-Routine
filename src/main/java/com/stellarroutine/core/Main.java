package com.stellarroutine.core;

import com.stellarroutine.entities.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Bebou");
        Game game = new Game(player);

        game.run();
    }
}
