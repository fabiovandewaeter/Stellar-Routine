package com.stellarroutine.entities;

import java.util.ArrayList;
import java.util.List;

import com.stellarroutine.core.Printable;
import com.stellarroutine.items.Item;

public class Inventory {
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public void removeItem(int index) {
        if (index < items.size()) {
            items.remove(index);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public String toString() {
        return String.format("""
                == Inventory description ==

                [ITEMS] (examine) (drop)
                %s""",
                Printable.prettyPrintIndexedList(items));
    }
}
