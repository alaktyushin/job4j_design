package ru.job4j.cache;

import java.io.File;
import java.io.FileInputStream;
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
        System.out.println("Non-interactive mode...");
        System.out.println("cachingDir is " + cachingDir);
        System.out.println("key is " + key);
        String file = cachingDir + "/" + key;
        if (DirFileCache.this.get(key) == null) {
            System.out.println("Trying to load file from disk.");
            try (FileInputStream in = new FileInputStream(file)) {
                String string = Files.readString(Path.of(cachingDir, key));
                DirFileCache.this.put(key, string);
                System.out.println(DirFileCache.this.get(key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File already loaded to cache.");
            System.out.println(DirFileCache.this.get(key));
        }
        return DirFileCache.this.get(key);
    }

}
