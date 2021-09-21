package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, Path> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        Optional<Path> rsl = Optional.ofNullable(map.put(fileProperty, file.toAbsolutePath()));
        if (rsl.isPresent()) {
            System.out.println("Duplicate is found (name/size):");
            System.out.println(fileProperty.getName() + "\t" + fileProperty.getSize());
            System.out.println(file.toAbsolutePath());
            System.out.println(rsl.get());
            System.out.println();
        }
        return super.visitFile(file, attrs);
    }
}