package com.stellarroutine.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stellarroutine.core.Printable;
import com.stellarroutine.entities.Entity;

public class Room implements Printable {
    private String name;
    private final List<Entity> entities;
    private final Map<Direction, Room> neighbors;
    private final List<Room> exits;
    private final List<Structure> structures;

    public Room(String name) {
        this.name = name;
        this.entities = new ArrayList<>();
        this.neighbors = new HashMap<>();
        this.exits = new ArrayList<>();
        this.structures = new ArrayList<>();
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

    public void addNeighbor(Room neighbor) {
        exits.add(neighbor);
    }

    public void addNeighbor(Direction direction, Room neighbor) {
        neighbors.put(direction, neighbor);
    }

    public Room getExit(int index) {
        if (index >= exits.size()) {
            return null;
        }
        return exits.get(index);
    }

    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public Structure getStructure(int index) {
        if (index >= structures.size()) {
            return null;
        }
        return structures.get(index);
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public String toString() {
        String directionRoomDescription = "";
        for (Direction direction : Direction.values()) {
            if (neighbors.get(direction) != null) {
                directionRoomDescription += " * (" + direction + ") " + neighbors.get(direction).getName() + "\n";
            }
        }
        return String.format("""
                == Room description ==
                Name: %s

                [ENTITIES] (talk)
                %s
                [EXITS] (go)
                %s%s
                [STRUCTURES] (open)
                %s""",
                name,
                Printable.prettyPrintIndexedList(entities),
                directionRoomDescription,
                Printable.prettyPrintIndexedList(exits),
                Printable.prettyPrintIndexedList(structures));
    }
}
