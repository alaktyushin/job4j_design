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
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FindFiles {

    private String logPath;
    private Predicate<Path> condition;

    private void saveLog(List<Path> log) {
        final boolean LOG_APPEND = false;
        final String LOG_HEAD =
                System.lineSeparator()
                        + "Search started on: "
                        + new Date()
                        + System.lineSeparator();
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(logPath, StandardCharsets.UTF_8, LOG_APPEND))) {
            pw.print(LOG_HEAD);
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private void setCondition(String searchType, String searchArg) throws PatternSyntaxException {
        condition =
            switch (searchType) {
                case "name" -> p -> p.toFile().getName().equals(searchArg);
                case "mask" -> p -> Pattern
                        .compile(searchArg
                                .replace(".", "[.]")
                                .replace("*", ".*")
                                .replace("?", "."))
                        .matcher(p.toFile().getName())
                        .matches();
                case "regex" -> p -> Pattern
                        .compile(searchArg)
                        .matcher(p.toFile().getName())
                        .matches();
                default -> p -> true;
            };
    }

    private void handle(ArgsName argsName) throws IOException {
        Path start = Path.of(argsName.get("d")).normalize();
        logPath = argsName.get("o");
        setCondition(argsName.get("t"), argsName.get("n"));
        saveLog(search(start, condition));
    }

    public static void main(String[] args) throws IOException {
        FindFiles findFiles = new FindFiles();
        findFiles.handle(ArgsName.of(args));
    }
}
