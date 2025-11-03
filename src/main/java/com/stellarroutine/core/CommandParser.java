package com.stellarroutine.core;

import java.util.Scanner;

public class CommandParser {
    private Scanner scanner;

    public CommandParser() {
        this.scanner = new Scanner(System.in);
    }

    public Command parse() {
        String input = scanner.nextLine();
        String cleanedInput = input.toLowerCase().trim();

        if (cleanedInput.isEmpty()) {
            return new Command(CommandType.NULL, null);
        }

        String[] words = cleanedInput.split("\\s+");
        String commandTypeWord = words[0];
        String target = "";

        if (words.length > 1) {
            target = cleanedInput.substring(commandTypeWord.length()).trim();
        }

        return new Command(findCommandType(commandTypeWord), target);
    }

    private CommandType findCommandType(String commandTypeWord) {
        CommandType commandType = CommandType.NULL;

        switch (commandTypeWord) {
            case "look":
                commandType = CommandType.LOOK;
                break;
            case "examine":
                commandType = CommandType.EXAMINE;
                break;
            case "take":
                commandType = CommandType.TAKE;
                break;
            case "talk":
                commandType = CommandType.TALK;
                break;
            case "go":
                commandType = CommandType.GO;
                break;

            case "profile":
                commandType = CommandType.PROFILE;
                break;
            case "inventory":
                commandType = CommandType.INVENTORY;
                break;

            case "help":
                commandType = CommandType.HELP;
                break;

            default:
                break;
        }

        return commandType;
    }
}
