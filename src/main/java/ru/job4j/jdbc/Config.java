package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values.putAll(read
                    .lines()
                    .filter(l -> !l.contains("#") && !l.equals(""))
                    .collect(Collectors.toMap(
                            k -> {
                                String[] str = k.split("=");
                                if (str.length < 2) {
                                    throw new IllegalArgumentException("Missing value!");
                                }
                                if (str[0].length() == 0) {
                                    throw new IllegalArgumentException("Missing key!");
                                }
                                return str[0];
                            },
                            v -> v.split("=")[1]))
            );
        } catch (IOException e) {
            System.out.println("File not found: " + path);
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}