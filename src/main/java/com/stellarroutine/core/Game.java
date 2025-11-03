package com.stellarroutine.core;

import java.util.ArrayDeque;
import java.util.Deque;

import com.stellarroutine.core.context.Context;
import com.stellarroutine.core.context.ExplorationContext;
import com.stellarroutine.entities.EntityManager;
import com.stellarroutine.entities.Player;

public class Game {
    private final EntityManager entityManager;
    private boolean running;
    private final CommandParser commandParser;
    private final Deque<Context> contexts;

    public Game(Player player) {
        this.entityManager = new EntityManager(player);
        this.running = true;
        this.commandParser = new CommandParser();
        this.contexts = new ArrayDeque<>();
        this.contexts.push(new ExplorationContext());
    }

    public void run() {
        System.out.println("== Start ==");
        while (running) {
            System.out.print(contexts.peek().getPrompt(this));
            Command command = commandParser.parse();
            if (command.getType() == null) {
                continue;
            }
            contexts.peek().handle(command, this);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Player getPlayer() {
        return entityManager.getPlayer();
    }

    public void pushContext(Context context) {
        contexts.push(context);
    }

    public void popContext() {
        contexts.pop();
    }
}
