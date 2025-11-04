package com.stellarroutine.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.stellarroutine.core.Game;
import com.stellarroutine.items.Item;
import com.stellarroutine.items.ItemType;

public class ScrapSpawnManger {
    private final double scrapSpawnChance;
    private final long scrapCooldownMinutes;
    private final int maxScrapsPerRoom;
    private final Map<Room, Long> lastSpawnByRoom;

    public ScrapSpawnManger(double scrapSpawnChance, long scrapCooldownMinutes, int maxScrapsPerRoom) {
        this.scrapSpawnChance = scrapSpawnChance;
        this.scrapCooldownMinutes = scrapCooldownMinutes;
        this.maxScrapsPerRoom = maxScrapsPerRoom;
        this.lastSpawnByRoom = new HashMap<>();
    }

    public void spawnScrap(Game game, Room room) {
        long now = game.getCurrentTimeMinutes();
        Long last = lastSpawnByRoom.get(room);
        if (last != null && (now - last) < scrapCooldownMinutes) {
            return;
        }

        int count = 0;
        List<Item> items = room.getItems();
        for (Item item : items) {
            if (item.getType().equals(ItemType.SCRAP)) {
                count++;
            }
        }
        if (count >= maxScrapsPerRoom) {
            return;
        }

        double roll = ThreadLocalRandom.current().nextDouble();
        if (roll < scrapSpawnChance) {
            room.addItem(new Item(ItemType.SCRAP, "scrap"));
            lastSpawnByRoom.put(room, now);
            return;
        }
    }
}
