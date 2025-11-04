package com.stellarroutine.core.context;

import com.stellarroutine.core.Command;
import com.stellarroutine.core.CommandType;
import com.stellarroutine.core.Game;
import com.stellarroutine.entities.Player;
import com.stellarroutine.items.Item;
import com.stellarroutine.map.Chest;

public class ChestContext implements Context {
    private final Chest chest;

    public ChestContext(Chest chest) {
        this.chest = chest;
    }

    @Override
    public String getPrompt(Game game) {
        return "[Chest]> ";
    }

    @Override
    public boolean handle(Command command, Game game) {
        switch (command.getType()) {
            case LOOK:
                executeLook(command, game);
                return true;
            case EXAMINE:
                executeExamine(command, game);
                return true;
            case TAKE:
                executeTake(command, game);
                return true;
            case DROP:
                executeDrop(command, game);
                return true;
            case OPEN:
                return false;
            case CLOSE:
                executeClose(command, game);
                return true;
            case TALK:
                return false;
            case GO:
                return false;

            case PROFILE:
                executeProfile(command, game);
                return true;
            case INVENTORY:
                executeInventory(command, game);
                return true;

            case HELP:
                executeHelp(game);
                return true;
        }
        System.out.println("Unknown command");
        return false;
    }

    private void executeLook(Command command, Game game) {
        System.out.println(chest);
    }

    private void executeExamine(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Examine what ?");
            return;
        }
    }

    private void executeTake(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Take what ?");
            return;
        }
        int index = Integer.parseInt(target);
        Item item = chest.getItem(index);
        if (item != null) {
            chest.removeItem(index);
            game.getPlayer().addItem(item);
        }
    }

    private void executeDrop(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Drop what ?");
            return;
        }
        int index = Integer.parseInt(target);
        Player player = game.getPlayer();
        Item item = player.getInventory().getItem(index);
        if (item != null) {
            player.getInventory().removeItem(index);
            chest.addItem(item);
        }
    }

    private void executeClose(Command command, Game game) {
        game.popContext();
    }

    private void executeProfile(Command command, Game game) {
        System.out.println(game.getPlayer());
    }

    private void executeInventory(Command command, Game game) {
        System.out.println(game.getPlayer().getInventory());
    }

    private void executeHelp(Game game) {
        System.out.println(CommandType.printHelp());
    }
}
