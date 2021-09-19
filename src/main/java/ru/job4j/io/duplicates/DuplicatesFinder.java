package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Map<Long, String> map = new HashMap<>();
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("/Users/lan/IdeaProjects/job4j_design/data"), visitor);
        //for (var v : visitor.getFileMap().entrySet()) {
            //System.out.println(v.getValue().getFileName() + "\t" + v.getValue().toFile().length());
            //String rsl = map.put(v.getValue().getSize(), v.getValue().getName());
            //if (rsl != null) System.out.println(v.getValue().getName() + "\t" + rsl);
        //}
            //String name = v.getKey().getFileName().toString();
            //System.out.println(name);
            //Optional<FileProperty> tmp = Optional.ofNullable(map.putIfAbsent(v.getKey(), v.getValue()));
            //if (!tmp.equals(null)) {
                //System.out.println(v.getKey());
                //System.out.println(tmp.toString());
                //System.out.println(v.getValue().getName() + "\t" + v.getValue().getSize());
                //System.out.println();
            //}
            //if (!map.put(v.getKey(), tmp).equals(null)) {
            //System.out.println(map.put(v.getKey().getFileName(), v.getValue()));
            //}


        //for (var v : visitor.getFileMap().entrySet()) {
        //    System.out.println(v.getKey() + "\t" + v.getValue());
        //}
         //map.entrySet().forEach(System.out::println);
    }
}