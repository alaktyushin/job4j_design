package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Emulator {

    public static String getDir() throws IOException {
        String defaultCachingDir = "./data/txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter Dir name: ");
        String dir = br.readLine();
        if (!Files.exists(Path.of(dir))) {
            dir = defaultCachingDir;
        }
        Files.list(Path.of(dir)).forEach(System.out::println);
        return dir;
    }

    public static String getName() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter File name: ");
        return br.readLine();
    }
}
