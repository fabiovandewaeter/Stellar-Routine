package com.stellarroutine.entities;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities;
    private Player player;

    public EntityManager(Player player) {
        this.entities = new ArrayList<>();
        addEntity(player);
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void addEntity(Player player) {
        if (!player.equals(this.player)) {
            this.entities.add(player);
            this.player = player;
        }
    }

    public Player getPlayer() {
        return player;
    }
}
