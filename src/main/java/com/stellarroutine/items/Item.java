package com.stellarroutine.items;

import com.stellarroutine.core.Printable;

public class Item implements Printable {
    private final ItemType type;
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
        return String.format("""
                == Item description ==
                Name: %s
                Type: %s""",
                name,
                type);
    }
}
