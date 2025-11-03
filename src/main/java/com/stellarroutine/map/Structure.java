package com.stellarroutine.map;

import com.stellarroutine.core.Game;

public abstract class Structure {
    protected String name;

    public Structure(String name) {
        this.name = name;
    }

    public abstract void interact(Game game);

    public String getName() {
        return name;
    }
}
