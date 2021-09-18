package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {

    Predicate<Path> condition;
    Path path;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() throws IOException {
        return Files.walk(path)
                    .filter(condition)
                    .toList();
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
