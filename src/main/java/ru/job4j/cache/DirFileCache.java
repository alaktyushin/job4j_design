package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        System.out.println("File mode...");
        System.out.println("CachingDir is: " + cachingDir);
        System.out.println("Key is: " + key);
        System.out.println();
        String string;
        try {
            string = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return string;
    }

}
