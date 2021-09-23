package ru.job4j.io;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private static Path start;
    private static String extension;

    private static Search of(String[] args) {
        Search validatedSearch = new Search();
        validatedSearch.validateArgs(args);
        return validatedSearch;
    }

    public static void main(String[] args) throws IOException {
        Search.of(args);
        System.out.println("Start dir: " + args[0] + "\t File ends with: " + args[1]);
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Missing arguments. Usage: java -jar Search.jar ROOT_FOLDER EXTENSION");
        }
        start = Path.of(args[0]);
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(start + " doesn't exist.");
        }
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException(start + " is not a directory.");
        }
        extension = args[1];
    }
}