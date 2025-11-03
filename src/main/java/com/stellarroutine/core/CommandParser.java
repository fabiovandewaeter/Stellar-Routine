package com.stellarroutine.core;

import java.util.Scanner;

public class CommandParser {
    private final Scanner scanner;

    public CommandParser() {
        this.scanner = new Scanner(System.in);
    }

    public Command parse() {
        String input = scanner.nextLine();
        String cleanedInput = input.toLowerCase().trim();

        if (cleanedInput.isEmpty()) {
            return new Command(null, null);
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
        switch (commandTypeWord) {
            case "look":
            case "ls":
                return CommandType.LOOK;
            case "examine":
                return CommandType.EXAMINE;
            case "take":
                return CommandType.TAKE;
            case "drop":
                return CommandType.DROP;
            case "open":
            case "cd":
                return CommandType.OPEN;
            case "close":
                return CommandType.CLOSE;
            case "talk":
                return CommandType.TALK;
            case "go":
                return CommandType.GO;

            case "profile":
            case "p":
                return CommandType.PROFILE;
            case "inventory":
            case "i":
                return CommandType.INVENTORY;

            case "help":
            case "h":
                return CommandType.HELP;

            default:
                break;
        }

        return null;
    }
}
