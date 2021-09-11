package ru.job4j.io;

import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            //System.out.println(text);
            //int[] numbers = text.chars().toArray();
            int[] numbers = text.codePoints().toArray();
            for (var i : numbers) System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}