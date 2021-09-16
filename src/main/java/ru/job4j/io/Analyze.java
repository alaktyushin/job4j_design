package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Analyze {

    public static void unavailable(String source, String target) {
        String beginning = "";
        String end;
        List<String> stringList = new LinkedList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            stringList = read
                    .lines()
                    .toList();
        } catch (IOException e) {
            System.out.println("File not found: " + source);
            //e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            if (stringList != null) {
                for (var v : stringList) {
                    String[] pair = v.split(" ");
                    if (pair[0].equals("400") || pair[0].equals("500")) {
                        beginning = (beginning.equals("")) ? pair[1] : beginning;
                    } else if (!beginning.equals("") && (pair[0].equals("200") || pair[0].equals("300"))) {
                        end = pair[1];
                        out.println(beginning + ";" + end);
                        beginning = "";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/unavailable1.csv", "./data/target1.txt");
        unavailable("./data/unavailable2.csv", "./data/target2.txt");
    }
}
