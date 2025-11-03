package com.stellarroutine.entities;

import java.util.ArrayList;
import java.util.List;

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
        if (index >= items.size()) {
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
        String description = "";

        description += "== Inventory description ==\n";
        description += "[ITEMS]\n";
        for (int i = 0; i < items.size(); i++) {
            description += i + ". " + items.get(i).getName() + "\n";
        }

        return description;
    }
}
