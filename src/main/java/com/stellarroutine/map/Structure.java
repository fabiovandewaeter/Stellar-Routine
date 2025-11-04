package com.stellarroutine.map;

import com.stellarroutine.core.Game;
import com.stellarroutine.core.Printable;

public abstract class Structure implements Printable {
    protected String name;

    public Structure(String name) {
        this.name = name;
    }

    public abstract void interact(Game game);

    public String getName() {
        return name;
    }
}
