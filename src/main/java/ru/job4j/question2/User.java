package ru.job4j.question2;

import java.util.Objects;

public class User {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /* Геттеры, сеттеры, equals() & hashCode() */

}
