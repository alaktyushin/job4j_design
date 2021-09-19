package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<String, List<Path>> fileMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String str = file.getFileName().toString() + " " + file.toFile().length();
        //System.out.println(str);
        //System.out.println(file.toAbsolutePath());
        //System.out.println(file.getFileName().toString() + "\t" + file.toFile().length() + "\t" + file.toAbsolutePath());
        //fileMap.put(new FileProperty(file.toFile().length(), file.getFileName().toString()), file.toAbsolutePath());
        List<Path> paths = new LinkedList<>();
        paths.add(file.toAbsolutePath());
        fileMap.put(str, paths);
        fileMap.keySet().forEach(System.out::println);

        return super.visitFile(file, attrs);
    }

    public Map<String, List<Path>> getFileMap() {
        return fileMap;
    }
}