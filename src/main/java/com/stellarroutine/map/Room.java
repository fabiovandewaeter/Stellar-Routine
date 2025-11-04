package com.stellarroutine.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stellarroutine.core.Printable;
import com.stellarroutine.entities.Entity;
import com.stellarroutine.entities.Player;
import com.stellarroutine.items.Item;

public class Room implements Printable {
    private String name;
    private final List<Entity> entities;
    private final Map<Direction, Room> neighbors;
    private final List<Room> exits;
    private final List<Structure> structures;
    private final List<Item> items;
    private Entity owner;
    private boolean buyable;
    private int price;

    public Room(String name) {
        this.name = name;
        this.entities = new ArrayList<>();
        this.neighbors = new HashMap<>();
        this.exits = new ArrayList<>();
        this.structures = new ArrayList<>();
        this.items = new ArrayList<>();
        this.buyable = false;
        this.price = -1;
        this.owner = null;
    }

    public Room(String name, boolean buyable, int price) {
        this(name);
        this.buyable = buyable;
        this.price = price;
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
        if (index < 0 || index >= exits.size()) {
            return null;
        }
        return exits.get(index);
    }

    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public Structure getStructure(int index) {
        if (index < 0 || index >= structures.size()) {
            return null;
        }
        return structures.get(index);
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            items.remove(index);
        }
    }

    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public List<Item> getItems() {
        return items;
    }

    public void changeOwner(Entity newOwner) {
        if (owner != null) {
            owner.removeOwnedRoom(this);
        }
        owner = newOwner;
    }

    public Entity getOwner() {
        return owner;
    }

    public boolean isBuyable() {
        return buyable;
    }

    public void beBoughtBy(Entity buyer) {
        if (buyer.getCredits() >= price) {
            buyer.addOwnedRoom(this);
            buyer.removeCredit(price);
        }
    }

    public void setBuyable(boolean buyable) {
        this.buyable = buyable;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        String directionRoomDescription = "";
        for (Direction direction : Direction.values()) {
            if (neighbors.get(direction) != null) {
                directionRoomDescription += " * (" + direction + ") " + neighbors.get(direction).getName() + "\n";
            }
        }
        List<Entity> entitiesExceptPlayer = entities.stream()
                .filter(e -> !(e instanceof Player))
                .toList();
        return String.format("""
                == Room description ==
                Name: %s

                [ENTITIES] (talk)
                %s
                [EXITS] (go) (buy)
                %s%s
                [STRUCTURES] (open)
                %s
                [ITEMS] (take)
                %s""",
                name,
                Printable.prettyPrintIndexedList(entitiesExceptPlayer),
                directionRoomDescription,
                Printable.prettyPrintIndexedList(exits),
                Printable.prettyPrintIndexedList(structures),
                Printable.prettyPrintIndexedList(items));
    }
}
