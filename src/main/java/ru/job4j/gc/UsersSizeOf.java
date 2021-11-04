package ru.job4j.gc;

import org.openjdk.jol.info.ClassLayout;

import java.util.LinkedList;
import java.util.List;

public class UsersSizeOf {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static class A {}

    public static class B {
        private int a;
    }

    public static class C {
        private int a;
        private long s;
    }

    public static class D {
        private int a;
        private String s;
    }

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        /*final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");*/
        System.out.printf("Free memory: %d%n", freeMemory / KB);
        /*System.out.printf("Total: %d%n", totalMemory / KB);
        System.out.printf("Max: %d%n", maxMemory / KB);
        */
    }

    public static void main(String[] args) throws OutOfMemoryError {
        List<User> users = new LinkedList<>();
        /*info();*/
        /* Examples of empty objects memory*/
        /*A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        D d = new D();
        System.out.println(ClassLayout.parseInstance(d).toPrintable());
        int[] aa = new int[0];
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
        */
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        User user1 = new User();
        System.out.println(ClassLayout.parseInstance(user1).toPrintable());
        User user2 = new User(1, "Aa");
        System.out.println(ClassLayout.parseInstance(user2).toPrintable());
        System.out.println(ClassLayout.parseInstance(user2.getAge()).toPrintable());
        System.out.println(ClassLayout.parseInstance(user2.getName()).toPrintable());

        System.gc();
        /* Examples of system GC
        info();
            for (int i = 0; i < 10000; i++) {
                User user = new User(i, "User" + i);
                users.add(user);
                ClassLayout.parseInstance(users).toPrintable();
                info();
            }

         */
    }
}
