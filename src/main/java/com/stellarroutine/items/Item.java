package com.stellarroutine.items;

public class Item {
    private ItemType type;
    private String name;

    public Item(ItemType type, String name) {
        this.type = type;
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String description = "";

        description += "== Item Descriptio n ==\n";
        description += "Name: " + name + "\n";
        description += "Item type: " + type.name() + "\n";

        return description;
    }
}
