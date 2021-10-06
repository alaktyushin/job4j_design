package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] str = text.toString().split(System.lineSeparator());
            int[] ints = new int[str.length];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = Integer.parseInt(str[i]);
                System.out.print("Number " + ints[i] + " is ");
                System.out.println((ints[i] % 2 == 0) ? "even." : "not even.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}