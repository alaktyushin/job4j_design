package ru.job4j.question;

import java.util.*;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int unchanged = 0;

        for (User userPrev : previous) {
            for (User userCurr : current) {
                if (userPrev.getId() == userCurr.getId()) {
                    unchanged++;
                    if (!Objects.equals(userPrev.getName(), userCurr.getName())) {
                        changed++;
                    }
                    break;
                }
            }
        }
        return new Info(current.size() - unchanged, changed, previous.size() - unchanged);
    }
}