package com.stellarroutine.entities;

import java.util.ArrayList;
import java.util.List;

import com.stellarroutine.items.Item;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String toString() {
        String description = "";

        description += "== Inventory ==\n";
        description += "Items: [";
        for (int i = 0; i < items.size(); i++) {
            if (i >= 1) {
                description += ", ";
            }
            Item item = items.get(i);
            description += item.getName() + " | " + item.getType();
        }
        description += "]\n";

        return description;
    }
}
