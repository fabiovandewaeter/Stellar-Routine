package com.stellarroutine.core;

public enum CommandType {
    LOOK("look - List things in the current room"),
    EXAMINE("examine [target] - Examine an object"),
    TAKE("take [target] - Take an object"),
    DROP("drop [target] - Drop an object on the floor or in an openned chest"),
    OPEN("open [target] - Open a chest (ex: open 2)"),
    CLOSE("close - Close the current chest"),
    TALK("talk [target] - Talk to an NPC"),
    GO("go [target] - Go to another room (ex: go north, go 0)"),

    PROFILE("profile - Player's profile"),
    INVENTORY("inventory - Player's inventory"),

    HELP("help - List commands");

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
