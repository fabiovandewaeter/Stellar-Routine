package com.stellarroutine.core;

import java.util.List;

public interface Printable {
    public String getName();

    public String toString();

    public static String prettyPrintIndexedList(List<? extends Printable> list) {
        String description = "";
        for (int i = 0; i < list.size(); i++) {
            description += " * (" + i + ") " + list.get(i).getName() + "\n";
        }

        return description;
    }
}
