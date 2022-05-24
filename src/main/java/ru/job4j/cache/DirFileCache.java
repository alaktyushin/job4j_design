package ru.job4j.cache;

import java.io.File;
import java.io.FileInputStream;

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
                StringBuilder text = new StringBuilder();
                int read;
                while ((read = in.read()) != -1) {
                    text.append((char) read);
                }
                DirFileCache.this.put(key, text.toString());
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