package com.stellarroutine.core.context;

import com.stellarroutine.core.Command;
import com.stellarroutine.core.CommandType;
import com.stellarroutine.core.Game;
import com.stellarroutine.entities.Player;
import com.stellarroutine.items.Item;
import com.stellarroutine.map.Room;

public class InventoryContext implements Context {

    public InventoryContext() {
    }

    @Override
    public String getPrompt(Game game) {
        return "[Inventory]> ";
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
                return false;
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
            case BUY:
                return false;

            case PROFILE:
                executeProfile(command, game);
                return true;
            case INVENTORY:
                executeClose(command, game);
                return true;

            case HELP:
                executeHelp(game);
                return true;
        }
        game.setPreviousResult("Unknown command");
        return false;
    }

    private void executeLook(Command command, Game game) {
        game.setPreviousResult(game.getPlayer().getInventory().toString());
    }

    private void executeExamine(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            game.setPreviousResult("Examine what ?");
            return;
        }
        try {
            int index = Integer.parseInt(target);
            Player player = game.getPlayer();
            Item item = player.getItem(index);
            if (item != null) {
                game.setPreviousResult(item.toString());
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeDrop(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            game.setPreviousResult("Drop what ?");
            return;
        }
        try {
            int index = Integer.parseInt(target);
            Player player = game.getPlayer();
            Item item = player.getItem(index);
            if (item != null) {
                Room room = player.getCurrentRoom();
                if (room != null) {
                    player.removeItem(index);
                    room.addItem(item);
                }
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeClose(Command command, Game game) {
        game.popContext();
    }

    private void executeProfile(Command command, Game game) {
        game.setPreviousResult(game.getPlayer().toString());
    }

    private void executeHelp(Game game) {
        game.setPreviousResult(CommandType.printHelp());
    }
}
