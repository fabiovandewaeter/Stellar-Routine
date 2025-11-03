package com.stellarroutine.core;

public enum CommandType {
    // list things in the current room
    LOOK("look - List things in the current room"),
    // examine an object
    EXAMINE("examine [target] - Examine an object"),
    // take an object
    TAKE("take [target] - Take an object"),
    // take to an NPC
    TALK("talk [target] - Talk to an NPC"),
    // go to another room
    GO("go [target] - Go to another room (ex: go north, go 5,5)"),

    // player's profile
    PROFILE("profile - Player's profile"),
    // player's inventory
    INVENTORY("inventory - Player's inventory"),

    HELP("help - List commands"),
    NULL("Unknown command");

    private final String description;

    private CommandType(String description) {
        this.description = description;
    }

    public static String printHelp() {
        String description = "";

        description += "Available commands: \n";

        for (CommandType commandType : CommandType.values()) {
            description += "    " + commandType.description + "\n";
        }

        return description;
    }
}
