package ru.job4j.ood.ocp;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    public String cachingDir;

    protected final Map<String, SoftReference<String>> cache = new HashMap<>();

    public void put(String key, String value) {
        cache.put(key, new SoftReference(value));
    }

    public String get(String key) {
        String value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

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

/* В данном случае 3 нарушения принципа OCP (принципа открытости/закрытости):

 * 1. Использование изначально заданных типов String.
 * Если понадобится использовать
 * кэш с другими типами значений - не всегда будет возможно наследоваться от String.
 *
 * 2. Использование метода load() внутри основного класса.
 * При работе с иными типами хранилищ информации необходимо будет переопределять
 * данный метод. Лучше сразу использовать абстракцию, а способы работы с данными из
 * различных источников реализовывать отдельно.
 *
 * 3. Использование глобальной переменной cachingDir делает затруднительным
 * контролировать возможность ее изменения вне данного класса.
 * */