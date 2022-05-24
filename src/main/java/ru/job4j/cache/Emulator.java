package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Emulator extends AbstractCache<String, String> {
    public Emulator(String defaultCachingDir) throws IOException {
        System.out.println("Interactive mode...");
        int choice;
        String name;
        String cachingDir = defaultCachingDir;
        do {
            choice = menuInput();
            if (choice == 1) {
                name = nameInput();
                cachingDir = (Files.exists(Path.of(name)) ? name : defaultCachingDir);
                System.out.println("Caching Dir is: " + Path.of(cachingDir).toRealPath());
                Files.list(Path.of(cachingDir)).forEach(System.out::println);
            } else if (choice == 2) {
                name = nameInput();
                String path = Path.of(cachingDir).toRealPath() + "/" + name;
                System.out.println("Trying to load file from disk to cache...");
                try (FileInputStream in = new FileInputStream(path)) {
                    StringBuilder text = new StringBuilder();
                    int read;
                    while ((read = in.read()) != -1) {
                        text.append((char) read);
                    }
                    Emulator.this.put(name, text.toString());
                    System.out.println("File loaded.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (choice == 3) {
                name = nameInput();
                Emulator.this.load(name);
            }
        } while (choice != 4);

    }

    private int menuInput() throws IOException {
        int choice;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter 1-3:");
        System.out.println("1. Choose caching dir.");
        System.out.println("2. Load file into cache.");
        System.out.println("3. Read file from cache.");
        System.out.println("4 or anything else - Exit.");
        String answer = br.readLine();
        choice = (!answer.equals("1") & !answer.equals("2") & !answer.equals("3") ? 4 : Integer.parseInt(answer));
        return choice;
    }

    private String nameInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter name: ");
        return br.readLine();
    }

    @Override
    protected String load(String key) {
        if (Emulator.this.get(key) == null) {
            System.out.println("No such file in cache.");
        } else {
            System.out.println(Emulator.this.get(key));
        }
        return null;
    }
}
