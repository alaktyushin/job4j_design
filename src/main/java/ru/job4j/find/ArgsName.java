package ru.job4j.find;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Missing arguments. Usage: -d=PATH -n=CONDITION(optional) -t=MASK(optional) -o=FILENAME"
            );
        }
        for (var a : args) {
            if (!a.startsWith("-") || a.split("=").length < 2) {
                throw new IllegalArgumentException("Incorrect argument(s) format: must be -ARG=VALUE");
            }
            values.put(a.split("=")[0].split("-")[1], a.split("=")[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}