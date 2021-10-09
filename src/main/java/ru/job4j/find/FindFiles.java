package ru.job4j.find;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.PatternSyntaxException;

public class FindFiles {

    private static String logPath;
    private static Predicate<Path> condition;

    private static final boolean LOG_APPEND = true;
    private static final String LOG_HEAD =
            System.lineSeparator()
                    + "Search started on: "
                    + new Date()
                    + System.lineSeparator();

    private static void saveLog(List<Path> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(logPath, StandardCharsets.UTF_8, LOG_APPEND))) {
            pw.print(LOG_HEAD);
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void setCondition(String searchType, String searchArg) throws PatternSyntaxException {
        condition =
            switch (searchType) {
                case "name" -> p -> p.toFile().getName().equals(searchArg);
                case "mask" -> p -> p.toFile().getName().contains(searchArg);
                case "regex" -> p -> p.toFile().getName().matches(searchArg);
                default -> p -> true;
            };
    }

    public static void handle(ArgsName argsName) throws IOException {
        Path start = Path.of(argsName.get("d")).normalize();
        logPath = argsName.get("o");
        setCondition(argsName.get("t"), argsName.get("n"));
        saveLog(search(start, condition));
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        handle(jvm);
    }
}
