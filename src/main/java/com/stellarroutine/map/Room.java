package com.stellarroutine.map;

import java.util.ArrayList;
import java.util.List;

import com.stellarroutine.entities.Entity;

public class Room {
    private String name;
    private List<Entity> entities;

    public Room(String name) {
        this.name = name;
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public String getName() {
        return name;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public String toString() {
        String description = "";

        description += "== Room description ==\n";
        description += "Name: " + name + "\n";
        description += "Entities: [";
        for (int i = 0; i < entities.size(); i++) {
            description += entities.get(i).getName();
            if (i < entities.size() - 1) {
                description += ", ";
            }
        }
        description += "]\n";

        return description;
    }
}
