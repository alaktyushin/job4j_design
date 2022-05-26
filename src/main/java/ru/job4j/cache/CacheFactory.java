package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Path;

public class CacheFactory {
    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        String dir;
        String name;
        if (jvm.get("dir") == null || jvm.get("name") == null) {
            dir = Emulator.getDir();
            name = Emulator.getName();
        } else {
            dir = Path.of(jvm.get("dir")).toRealPath().toString();
            name = jvm.get("name");
        }
        AbstractCache cache = new DirFileCache(dir);
        System.out.println(cache.get(name));
        System.out.println();
        System.gc();
        System.out.println(cache.get(name));
    }
}