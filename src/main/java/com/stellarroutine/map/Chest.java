package com.stellarroutine.map;

import com.stellarroutine.core.Game;
import com.stellarroutine.core.context.ChestContext;
import com.stellarroutine.entities.Inventory;
import com.stellarroutine.items.Item;

public class Chest extends Structure {
    private final Inventory inventory;

    public Chest(String name) {
        super(name);
        this.inventory = new Inventory();
    }

    public Item getItem(int index) {
        return inventory.getItem(index);
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public void removeItem(int index) {
        inventory.removeItem(index);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void interact(Game game) {
        game.pushContext(new ChestContext(this));
    }

    public String toString() {
        String description = "";

        description += "== Chest description ==\n";
        description += "[ITEMS]\n";
        for (int i = 0; i < inventory.getItems().size(); i++) {
            description += i + ". " + inventory.getItems().get(i).getName() + "\n";
        }

        return description;
    }
}
