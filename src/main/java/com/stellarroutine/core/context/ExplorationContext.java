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
                return true;
            case OPEN:
                executeOpen(command, game);
                return true;
            case CLOSE:
                return true;
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

        return false;
    }

    private void executeLook(Command command, Game game) {
        game.getScrapSpawnManger().spawnScrap(game, game.getPlayer().getCurrentRoom());
        System.out.println(game.getPlayer().getCurrentRoom());
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
            Item item = player.getItem(index);
            if (item != null) {
                System.out.println(item);
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeTake(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Take what ?");
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
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeOpen(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Open what ?");
            return;
        }
        try {
            int index = Integer.parseInt(target);
            Room currentRoom = game.getPlayer().getCurrentRoom();
            Structure structure = currentRoom.getStructure(index);
            structure.interact(game);
        } catch (NumberFormatException ignored) {
        }
    }

    private void executeTalk(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("To to who ?");
            return;
        }
    }

    private void executeGo(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Go where ?");
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
            System.out.println("executeGo(): Unknown target");
        }
    }

    private void executeBuy(Command command, Game game) {
        String target = command.getTarget();
        if (target == null || target.isEmpty()) {
            System.out.println("Buy what ?");
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
                System.out.println("executeGo(): The target is not buyable");
            }
        } else {
            System.out.println("executeGo(): Unknown target");
        }
    }

    private void executeProfile(Command command, Game game) {
        System.out.println(game.getPlayer());
    }

    private void executeInventory(Command command, Game game) {
        game.pushContext(new InventoryContext());
        System.out.println(game.getPlayer().getInventory());
    }

    private void executeHelp(Game game) {
        System.out.println(CommandType.printHelp());
    }
}
