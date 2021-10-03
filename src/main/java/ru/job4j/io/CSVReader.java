package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {

    private static String delimiter;
    private static List<Integer> filterIndexes = new ArrayList<>();

    public static void handle(ArgsName argsName) throws IOException {
        List<List<String>> table = new ArrayList<>();
        var file = argsName.get("path");
        validateInput(file);
        var out = validateOutput(argsName.get("out"));
        delimiter = argsName.get("delimiter");
        List<String> filter = Arrays.stream(argsName.get("filter").split(",")).toList();
        try (Scanner scanner = new Scanner(new File(file))) {
            String str = scanner.nextLine();
            List<String> head = Arrays.stream(str.split(delimiter)).collect(Collectors.toList());
            str = filter.stream().collect(Collectors.joining(delimiter));
            out.println(str);
            for (int i = 0; i < head.size(); i++) {
                if (filter.contains(head.get(i))) {
                    filterIndexes.add(i);
                }
            }
            while (scanner.hasNextLine()) {
                table.add(getValueFromLine(scanner.nextLine()));
            }
        }
        for (var i : table) {
            for (var j : i) {
                out.print(j);
            }
            out.println();
        }
        out.close();
    }

    private static List<String> getValueFromLine(String line) {
        List<String> strings = new ArrayList<>();
        String[] str = line.split(delimiter);
        for (int i = 0; i < str.length; i++) {
            if (filterIndexes.contains(i)) {
                strings.add(str[i]);
                strings.add(delimiter);
            }
        }
        strings.remove(strings.size() - 1);
        return strings;
    }

    private static PrintStream validateOutput(String target) throws FileNotFoundException {
        if (target.equals("stdout")) {
            return new PrintStream(System.out);
        }
        return new PrintStream(target);
    }

    private static void validateInput(String path) {
        if (!Path.of(path).toFile().exists()) {
            throw new IllegalArgumentException(path + " doesn't exist.");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        handle(jvm);
    }
}
