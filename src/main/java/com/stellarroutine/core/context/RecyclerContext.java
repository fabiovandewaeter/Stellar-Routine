package com.stellarroutine.core.context;

import com.stellarroutine.core.Command;
import com.stellarroutine.core.CommandType;
import com.stellarroutine.core.Game;
import com.stellarroutine.entities.Player;
import com.stellarroutine.items.Item;

public class RecyclerContext implements Context {

    public RecyclerContext() {
    }

    @Override
    public String getPrompt(Game game) {
        return "[Recycler]> ";
    }

    @Override
    public boolean handle(Command command, Game game) {
        switch (command.getType()) {
            case LOOK:
                return false;
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
                executeInventory(command, game);
                return true;

            case HELP:
                executeHelp(game);
                return true;
        }
        System.out.println("Unknown command");
        return false;
    }

    private void executeExamine(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Examine what ?");
            return;
        }
        try {
            int index = Integer.parseInt(target);
            Player player = game.getPlayer();
            Item item = player.getInventory().getItem(index);
            if (item != null) {
                System.out.println(item);
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeDrop(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Drop what ?");
            return;
        }
        try {
            int index = Integer.parseInt(target);
            Player player = game.getPlayer();
            Item item = player.getItem(index);
            if (item != null) {
                int resalePrice = item.getResalePrice();
                player.addCredit(resalePrice);
                player.removeItem(index);
                System.out.println(" + " + resalePrice + " credits");
            }
        } catch (NumberFormatException ignored) {
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
