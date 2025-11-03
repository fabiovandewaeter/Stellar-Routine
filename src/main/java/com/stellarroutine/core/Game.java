package com.stellarroutine.core;

import com.stellarroutine.entities.EntityManager;
import com.stellarroutine.entities.Player;

public class Game {
    private EntityManager entityManager;
    private boolean running;
    private CommandParser commandParser;

    public Game(Player player) {
        this.entityManager = new EntityManager(player);
        this.running = true;
        this.commandParser = new CommandParser();
    }

    public void run() {
        System.out.println("== Start ==");
        while (running) {
            System.out.print("> ");
            Command command = commandParser.parse();
            switch (command.getType()) {
                case LOOK:
                    executeLook(command);
                    break;
                case EXAMINE:
                    executeExamine(command);
                    break;
                case TAKE:
                    executeTake(command);
                    break;
                case TALK:
                    executeTalk(command);
                    break;
                case GO:
                    executeGo(command);
                    break;

                case PROFILE:
                    executeProfile(command);
                    break;
                case INVENTORY:
                    executeInventory(command);
                    break;

                case HELP:
                    executeHelp();
                    break;

                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }

    private void executeLook(Command command) {
        System.out.println(entityManager.getPlayer().getCurrentRoom());
    }

    private void executeExamine(Command command) {
        if (command.getTarget().isEmpty()) {
            System.out.println("Examine what ?");
        } else {
        }
    }

    private void executeTake(Command command) {
        if (command.getTarget().isEmpty()) {
            System.out.println("Take what ?");
        } else {
        }
    }

    private void executeTalk(Command command) {
        if (command.getTarget().isEmpty()) {
            System.out.println("To to who ?");
        } else {
        }
    }

    private void executeGo(Command command) {
        if (command.getTarget().isEmpty()) {
            System.out.println("Go where ?");
        } else {
            // go to target if possible
        }
    }

    private void executeProfile(Command command) {
        System.out.println(entityManager.getPlayer());
    }

    private void executeInventory(Command command) {
    }

    private void executeHelp() {
        System.out.println(CommandType.printHelp());
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
