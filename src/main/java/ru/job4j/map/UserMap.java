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
        // {ru.job4j.map.User@7dab128d=java.lang.Object@a09ee92}
    }
}