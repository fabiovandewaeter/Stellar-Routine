package com.stellarroutine.entities;

public class Entity {
    protected String name;
    protected Inventory inventory;

    public Entity(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public String toString() {
        String description = "";

        description += "== Entity Description ==\n";
        description += "Name: " + this.name + "\n";

        return description;
    }
}
