package com.stellarroutine.map.structures;

import com.stellarroutine.core.Game;
import com.stellarroutine.core.context.RecyclerContext;

public class Recycler extends Structure {

    public Recycler(String name) {
        super(name);
    }

    @Override
    public void interact(Game game) {
        game.pushContext(new RecyclerContext());
    }

    public String toString() {
        return String.format("""
                == Recycler description ==
                drop items to get credits

                %s""");
    }
}
