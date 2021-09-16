package ru.job4j.io;

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
                                if (k.split("=").length < 2) {
                                    throw new IllegalArgumentException("Missing key or value!");
                                }
                                return k.split("=")[0];
                            },
                            v -> v.split("=")[1]))
            );
        } catch (IOException e) {
            System.out.println("File not found: " + path);
            //e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}