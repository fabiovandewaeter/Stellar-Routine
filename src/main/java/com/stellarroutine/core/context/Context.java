package com.stellarroutine.core.context;

import com.stellarroutine.core.Command;
import com.stellarroutine.core.Game;

public interface Context {

    public String getPrompt(Game game);

    public boolean handle(Command command, Game game);
}
