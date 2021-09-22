package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var v : sources) {
                zip.putNextEntry(new ZipEntry(v.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(v))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Missing arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        Path path = Path.of(argsName.get("d"));
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Source directory is not found: " + path);
        }
        String extension = argsName.get("e");
        Path target = Path.of(argsName.get("o"));
        System.out.println("Source directory: " + path);
        System.out.println("Extension to exclude: " + argsName.get("e"));
        System.out.println("Target file: " + argsName.get("o"));
        List<File> list = search(path, p -> !p.toFile().getName().endsWith(extension))
                .stream()
                .map(Path::toFile)
                .toList();
        System.out.println("Packing...");
        packFiles(list, new File(args[2].split("=")[1]));
        if (Files.exists(target) && target.toFile().length() > 0) {
            System.out.println("Packed successfully: " + target + "\t" + target.toFile().length() + " bytes");
        }
    }
}