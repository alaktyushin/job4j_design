package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}\n", name, age);
        boolean bool = true;
        char c = 'A';
        byte b = 1;
        short s = 17;
        int i = 30_000;
        long l = 28L;
        float f = 3.14f;
        double d = 3.14e70;
        LOG.info("Primitive variables:");
        LOG.info("boolean {}, char {}, byte {}, short {}, int {}, long {}, float {}, double {}", bool, c, b, s, i, l, f, d);
    }
}