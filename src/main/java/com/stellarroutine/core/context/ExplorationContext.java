package com.stellarroutine.core.context;

import com.stellarroutine.core.Command;
import com.stellarroutine.core.CommandType;
import com.stellarroutine.core.Game;
import com.stellarroutine.entities.Player;
import com.stellarroutine.items.Item;
import com.stellarroutine.map.Direction;
import com.stellarroutine.map.Room;
import com.stellarroutine.map.structures.Structure;

public class ExplorationContext implements Context {

    @Override
    public String getPrompt(Game game) {
        return "[" + game.getPlayer().getCurrentRoom().getName() + "]> ";
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
                game.setPreviousResult("Command does nothing in this context");
                return false;
            case OPEN:
                executeOpen(command, game);
                return true;
            case CLOSE:
                game.setPreviousResult("Command does nothing in this context");
                return false;
            case TALK:
                executeTalk(command, game);
                return true;
            case GO:
                executeGo(command, game);
                return true;
            case BUY:
                executeBuy(command, game);
                return true;

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

    private void executeLook(Command command, Game game) {
        game.getScrapSpawnManger().spawnScrap(game, game.getPlayer().getCurrentRoom());
        game.setPreviousResult(game.getPlayer().getCurrentRoom().toString());
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
            } else {
                game.setPreviousResult("Command did nothing");
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeTake(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            game.setPreviousResult("Take what ?");
            return;
        }
        try {
            int index = Integer.parseInt(target);
            Player player = game.getPlayer();
            Room room = player.getCurrentRoom();
            Item item = room.getItem(index);
            if (item != null) {
                room.removeItem(index);
                player.addItem(item);
            } else {
                game.setPreviousResult("Command did nothing");
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeOpen(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            game.setPreviousResult("Open what ?");
            return;
        }
        try {
            int index = Integer.parseInt(target);
            Room currentRoom = game.getPlayer().getCurrentRoom();
            Structure structure = currentRoom.getStructure(index);
            structure.interact(game);
            game.setPreviousResult("");
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeTalk(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            game.setPreviousResult("To to who ?");
            return;
        } else {
            game.setPreviousResult("Command did nothing");
        }
    }

    private void executeGo(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            game.setPreviousResult("Go where ?");
            return;
        }
        Player player = game.getPlayer();
        Room currentRoom = player.getCurrentRoom();
        Direction direction = Direction.from(command.getTarget());
        Room newRoom = null;
        if (direction != null) {
            newRoom = currentRoom.getNeighbor(direction);
        } else {
            try {
                int index = Integer.parseInt(target);
                newRoom = currentRoom.getExit(index);
            } catch (NumberFormatException ignored) {
            }
        }

        if (newRoom != null) {
            player.changeRoom(newRoom);
        } else {
            game.setPreviousResult("executeGo(): Unknown target");
        }
    }

    private void executeBuy(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            game.setPreviousResult("Buy what ?");
            return;
        }
        Player player = game.getPlayer();
        Room currentRoom = player.getCurrentRoom();
        Direction direction = Direction.from(command.getTarget());
        Room newRoom = null;
        if (direction != null) {
            newRoom = currentRoom.getNeighbor(direction);
        } else {
            try {
                int index = Integer.parseInt(target);
                newRoom = currentRoom.getExit(index);
            } catch (NumberFormatException ignored) {
            }
        }

        if (newRoom != null) {
            if (newRoom.isBuyable()) {
                player.buy(newRoom);
            } else {
                game.setPreviousResult("executeGo(): The target is not buyable");
            }
        } else {
            game.setPreviousResult("executeGo(): Unknown target");
        }
    }

    private void executeProfile(Command command, Game game) {
        game.setPreviousResult(game.getPlayer().toString());
    }

    private void executeInventory(Command command, Game game) {
        game.pushContext(new InventoryContext());
        game.setPreviousResult(game.getPlayer().getInventory().toString());
    }

    private void executeHelp(Game game) {
        game.setPreviousResult(CommandType.printHelp());
    }
}
