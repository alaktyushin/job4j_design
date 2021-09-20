package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles implements FileVisitor<Path> {

    Predicate<Path> condition;
    List<Path> paths = new LinkedList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return paths
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        paths.add(file.toAbsolutePath());
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }
}
