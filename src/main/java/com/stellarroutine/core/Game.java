package com.stellarroutine.core;

import java.util.ArrayDeque;
import java.util.Deque;

import com.stellarroutine.core.context.Context;
import com.stellarroutine.core.context.ExplorationContext;
import com.stellarroutine.entities.EntityManager;
import com.stellarroutine.entities.Player;
import com.stellarroutine.map.ScrapSpawnManger;

public class Game {
    private final EntityManager entityManager;
    private boolean running;
    private final CommandParser commandParser;
    private final Deque<Context> contexts;
    private ScrapSpawnManger scrapSpawnManger;
    private long currentTimeMinutes;

    public Game(Player player, ScrapSpawnManger scrapSpawnManger) {
        this.entityManager = new EntityManager(player);
        this.running = true;
        this.commandParser = new CommandParser();
        this.contexts = new ArrayDeque<>();
        this.contexts.push(new ExplorationContext());
        this.scrapSpawnManger = scrapSpawnManger;
        this.currentTimeMinutes = 0;
    }

    public void run() {
        System.out.println("== Start ==");
        while (running) {
            System.out.print(contexts.peek().getPrompt(this));
            Command command = commandParser.parse();
            if (command.getType() == null) {
                continue;
            }
            currentTimeMinutes += 5;
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

    public ScrapSpawnManger getScrapSpawnManger() {
        return scrapSpawnManger;
    }

    public long getCurrentTimeMinutes() {
        return currentTimeMinutes;
    }
}
