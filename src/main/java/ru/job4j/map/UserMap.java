package ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserMap {

    static Map<User, Object> user = new HashMap<>();

    public static void main(String[] args) {
        User user01 = new User("Ivan", 3, new GregorianCalendar(1991, 01, 01));
        User user02 = new User("Ivan", 3, new GregorianCalendar(1991, 01, 01));
        user.put(user01, new Object());
        user.put(user02, new Object());
        System.out.println(user);
        // Вывод на печать:
        // {ru.job4j.map.User@30f39991=java.lang.Object@452b3a41, ru.job4j.map.User@a09ee92=java.lang.Object@4a574795}
    }
}