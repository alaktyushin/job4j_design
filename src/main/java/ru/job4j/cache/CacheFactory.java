package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Path;

public class CacheFactory {
    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        if (jvm.get("dir") == null || jvm.get("name") == null) {
            AbstractCache cache = new Emulator("./data/txt");
        } else {
            AbstractCache cache = new DirFileCache(
                    Path.of(jvm.get("dir")).toRealPath().toString());
            cache.load(jvm.get("name"));
        }
    }
}