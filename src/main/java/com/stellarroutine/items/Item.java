package com.stellarroutine.items;

import com.stellarroutine.core.Printable;

public class Item implements Printable {
    private final ItemType type;
    private String name;
    private final int resalePrice;

    public Item(ItemType type, String name) {
        this(type, name, 0);
    }

    public Item(ItemType type, String name, int resalePrice) {
        this.type = type;
        this.name = name;
        this.resalePrice = resalePrice;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getResalePrice() {
        return resalePrice;
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
