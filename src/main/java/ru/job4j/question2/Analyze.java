package ru.job4j.question2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed;
        int unchanged = 0;
        int deleted;
        String temp;

        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }
        for (User user : current) {
            temp = map.put(user.getId(), user.getName());
            if (Objects.equals(temp, user.getName())) {
                unchanged++;
            }
            if (Objects.equals(temp, null)) {
                added++;
            }
        }
        changed = current.size() - added - unchanged;
        deleted = previous.size() - changed - unchanged;
        return new Info(added, changed, deleted);
    }
}