package com.stellarroutine.core;

public class Command {
    private CommandType type;
    private String target;

    public Command(CommandType type, String target) {
        this.type = type;
        this.target = target;
    }

    public CommandType getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }
}
