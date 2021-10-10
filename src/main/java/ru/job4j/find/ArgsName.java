package ru.job4j.find;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Missing arguments. Usage: -d=PATH -n=CONDITION(optional) -t=MASK(optional) -o=LOG_FILENAME"
            );
        }
        for (var a : args) {
            if (!a.startsWith("-") || a.split("=").length != 2) {
                throw new IllegalArgumentException("Incorrect argument(s) format: must be -ARG=VALUE");
            }
            values.put(a.split("=")[0].split("-")[1], a.split("=")[1]);
        }
        if (values.get("d") == null) {
            throw new IllegalArgumentException("Missing -d value (PATH).");
        }
        if (values.get("o") == null) {
            throw new IllegalArgumentException("Missing -o value (LOG_FILENAME).");
        }
        if (!values.get("t").equals("name") && !values.get("t").equals("mask") && !values.get("t").equals("regex")) {
            System.out.println("Search type doesn't match \"name\", \"mask\" or \"regex\". All files will be shown.");
        }
    }

    static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}