package com.stellarroutine.entities;

import com.stellarroutine.map.Room;

public class Entity {
    protected String name;
    protected Inventory inventory;
    protected long credits;
    protected Room currentRoom;

    public Entity(String name, Room spawningRoom) {
        this.name = name;
        this.inventory = new Inventory();
        this.credits = 0;
        enterRoom(spawningRoom);
    }

    private void enterRoom(Room newRoom) {
        currentRoom = newRoom;
        newRoom.addEntity(this);
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String toString() {
        String description = "";

        description += "== Entity Descriptio n ==\n";
        description += "Name: " + name + "\n";
        description += "Credits: " + credits + "\n";
        description += "Current room: " + currentRoom.getName() + "\n";

        return description;
    }
}
