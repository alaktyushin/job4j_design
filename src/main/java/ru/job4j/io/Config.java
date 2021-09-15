package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> stringList = null;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            stringList = read
                    .lines()
                    .filter(l -> !l.contains("#") && !l.equals(""))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found: " + path);
            //e.printStackTrace();
        }
        if (stringList != null) {
            for (var v : stringList) {
                String[] pair = v.split("=");
                if (pair.length > 1) {
                    values.put(pair[0], pair[1]);
                } else throw new IllegalArgumentException("Missing key or value in string: " + v);
            }
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}