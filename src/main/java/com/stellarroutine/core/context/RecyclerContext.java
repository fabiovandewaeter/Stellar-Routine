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
                game.setPreviousResult("Command does nothing in this context");
                return false;
            case EXAMINE:
                executeExamine(command, game);
                return true;
            case TAKE:
                game.setPreviousResult("Command does nothing in this context");
                return false;
            case DROP:
                executeDrop(command, game);
                return true;
            case OPEN:
                game.setPreviousResult("Command does nothing in this context");
                return false;
            case CLOSE:
                executeClose(command, game);
                return true;
            case TALK:
                game.setPreviousResult("Command does nothing in this context");
                return false;
            case GO:
                game.setPreviousResult("Command does nothing in this context");
                return false;
            case BUY:
                game.setPreviousResult("Command does nothing in this context");
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
        game.setPreviousResult("Command does nothing in this context");
        return false;
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
            Item item = player.getInventory().getItem(index);
            if (item != null) {
                game.setPreviousResult(item.toString());
            } else {
                game.setPreviousResult("Command did nothing");
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
                int resalePrice = item.getResalePrice();
                player.addCredit(resalePrice);
                player.removeItem(index);
                game.setPreviousResult(" + " + resalePrice + " credits");
            } else {
                game.setPreviousResult("Command did nothing");
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeClose(Command command, Game game) {
        game.popContext();
        game.setPreviousResult("");
    }

    private void executeProfile(Command command, Game game) {
        game.setPreviousResult(game.getPlayer().toString());
    }

    private void executeInventory(Command command, Game game) {
        game.setPreviousResult(game.getPlayer().getInventory().toString());
    }

    private void executeHelp(Game game) {
        game.setPreviousResult(CommandType.printHelp());
    }
}
