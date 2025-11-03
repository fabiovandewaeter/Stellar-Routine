package com.stellarroutine.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stellarroutine.entities.Entity;

public class Room {
    private String name;
    private List<Entity> entities;
    private Map<Direction, Room> neighbors;

    public Room(String name) {
        this.name = name;
        this.entities = new ArrayList<>();
        this.neighbors = new HashMap<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public String getName() {
        return name;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Room getNeighbor(Direction direction) {
        return neighbors.get(direction);
    }

    public Map<Direction, Room> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Direction direction, Room neighbor) {
        this.neighbors.put(direction, neighbor);
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
        description += "Neighbors: [";
        int counter = 0;
        for (Direction direction : Direction.values()) {
            if (neighbors.get(direction) != null) {
                if (counter >= 1) {
                    description += ", ";
                }
                description += neighbors.get(direction).getName();
                counter++;
            }
        }
        description += "]\n";

        return description;
    }
}
