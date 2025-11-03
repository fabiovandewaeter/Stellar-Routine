package com.stellarroutine.core.context;

import com.stellarroutine.core.Command;
import com.stellarroutine.core.CommandType;
import com.stellarroutine.core.Game;
import com.stellarroutine.entities.Player;
import com.stellarroutine.map.Chest;
import com.stellarroutine.map.Direction;
import com.stellarroutine.map.Room;
import com.stellarroutine.map.Structure;

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
        System.out.println(game.getPlayer().getCurrentRoom());
    }

    private void executeExamine(Command command, Game game) {
        String target = command.getTarget();
        if (target.isEmpty()) {
            System.out.println("Examine what ?");
        } else {
        }
    }

    private void executeTake(Command command, Game game) {
        String target = command.getTarget();
        if (target.isEmpty()) {
            System.out.println("Take what ?");
        } else {
        }
    }

    private void executeOpen(Command command, Game game) {
        String target = command.getTarget();
        if (target.isEmpty()) {
            System.out.println("Open what ?");
        } else {
            Room currentRoom = game.getPlayer().getCurrentRoom();
            Structure chest = currentRoom.getStructure(Integer.parseInt(target));
            if (chest instanceof Chest) {
                chest.interact(game);
            } else {
                System.out.println("executeOpen(): can not open because the structure is not a Chest");
            }
        }
    }

    private void executeTalk(Command command, Game game) {
        String target = command.getTarget();
        if (target.isEmpty()) {
            System.out.println("To to who ?");
        } else {
        }
    }

    private void executeGo(Command command, Game game) {
        String target = command.getTarget();
        if (target.isEmpty()) {
            System.out.println("Go where ?");
        } else {
            Direction direction = Direction.from(command.getTarget());
            if (direction == null) {
                System.out.println("executeGo(): Unknown target");
                return;
            }
            Player player = game.getPlayer();
            Room currentRoom = player.getCurrentRoom();
            Room newRoom = currentRoom.getNeighbor(direction);
            if (newRoom == null) {
                System.out.println("executeGo(): destination inaccessible");
                return;
            }
            currentRoom.removeEntity(player);
            newRoom.addEntity(player);
            player.setCurrentRoom(newRoom);
        }
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
