package com.stellarroutine.core;

import com.stellarroutine.entities.Player;
import com.stellarroutine.items.Item;
import com.stellarroutine.items.ItemType;
import com.stellarroutine.map.Direction;
import com.stellarroutine.map.Room;
import com.stellarroutine.map.ScrapSpawnManger;
import com.stellarroutine.map.structures.Chest;
import com.stellarroutine.map.structures.Recycler;

public class Main {
    public static void main(String[] args) {
        Room defaultRoom = new Room("default");
        Room room2 = new Room("room2");
        Room room3 = new Room("room3");
        Room house = new Room("house", true, 10000);
        defaultRoom.addNeighbor(Direction.NORTH, room2);
        defaultRoom.addNeighbor(Direction.WEST, room3);
        defaultRoom.addNeighbor(house);
        room2.addNeighbor(Direction.SOUTH, defaultRoom);
        room3.addNeighbor(Direction.EAST, defaultRoom);
        house.addNeighbor(defaultRoom);

        Chest chest = new Chest("chest");
        defaultRoom.addStructure(chest);
        Recycler recycler = new Recycler("recycler");
        defaultRoom.addStructure(recycler);

        Player player = new Player("Bebou", defaultRoom);
        player.addCredit(100000000);
        Item item = new Item(ItemType.WEAPON, "Iron Sword");
        player.addItem(item);

        ScrapSpawnManger scrapSpawnManger = new ScrapSpawnManger(0.5, 50, 1);
        Game game = new Game(player, scrapSpawnManger);

        game.run();
    }
}
