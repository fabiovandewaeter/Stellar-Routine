package com.stellarroutine.entities;

import java.util.ArrayList;
import java.util.List;

import com.stellarroutine.core.Printable;
import com.stellarroutine.items.Item;
import com.stellarroutine.map.Room;

public class Entity implements Printable {
    protected String name;
    protected final Inventory inventory;
    protected long credits;
    protected Room currentRoom;
    protected List<Room> ownedRooms;

    public Entity(String name, Room spawningRoom) {
        this.name = name;
        this.inventory = new Inventory();
        this.credits = 0;
        this.ownedRooms = new ArrayList<>();
        enterRoom(spawningRoom);
    }

    private void enterRoom(Room newRoom) {
        currentRoom = newRoom;
        newRoom.addEntity(this);
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public long getCredits() {
        return credits;
    }

    public void removeCredit(long credits) {
        this.credits -= credits;
    }

    public void addCredit(long credits) {
        this.credits += credits;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void changeRoom(Room newRoom) {
        currentRoom.removeEntity(this);
        newRoom.addEntity(this);
        this.setCurrentRoom(newRoom);
    }

    public List<Room> getOwnedRooms() {
        return ownedRooms;
    }

    public void addOwnedRoom(Room room) {
        if (!ownedRooms.contains(room)) {
            ownedRooms.add(room);
        }
    }

    public void removeOwnedRoom(Room room) {
        ownedRooms.remove(room);
    }

    public void buy(Room room) {
        room.beBoughtBy(this);
    }

    public String toString() {
        return String.format("""
                == Entity description ==
                Name: %s
                Credits: %s
                Current Room: %s

                [OWNED ROOMS] (sell)
                %s""",
                name,
                credits,
                currentRoom.getName(),
                Printable.prettyPrintIndexedList(ownedRooms));
    }
}
