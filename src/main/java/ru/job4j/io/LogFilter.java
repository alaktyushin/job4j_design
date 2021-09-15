package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> stringList = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            stringList = in
                    .lines()
                    .filter(l -> l.contains("404"))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
    }
}
