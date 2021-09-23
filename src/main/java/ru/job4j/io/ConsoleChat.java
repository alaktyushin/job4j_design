package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ConsoleChat {

    private static String path;
    private static String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static List<String> log = new LinkedList<>();
    private static List<String> answers = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        ConsoleChat.path = path;
        ConsoleChat.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        String q;
        String a;
        boolean flag = true;
        do {
            q = consoleInput();
            log.add("q: " + q);
            flag = !q.toLowerCase(Locale.ROOT).equals(STOP) && flag;
            flag = q.toLowerCase(Locale.ROOT).equals(CONTINUE) || flag;
            if (flag) {
                a = answers.get((int) (Math.random() * answers.size()));
                System.out.println(a);
                log.add("a: " + a);
            }
        } while (!q.toLowerCase(Locale.ROOT).equals(OUT));
    }

    private List<String> readPhrases() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String consoleInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ваш вопрос: ");
        return br.readLine();
    }

    private static void validateArgs(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Missing arguments. Usage ConsoleChat DIR_log DIR_botAnswers");
        }
        if (!Files.exists(Path.of(args[1]))) {
            throw new FileNotFoundException("File " + args[1] + " does not exist.");
        }
        path = args[0];
        botAnswers = args[1];
    }

    public static void main(String[] args) throws IOException {
        //args: log.txt botAnswers.txt
        //working directory: /Users/lan/IdeaProjects/job4j_design/src/data
        validateArgs(args);
        ConsoleChat cc = new ConsoleChat(path, botAnswers);
        cc.readPhrases();
        cc.run();
        cc.saveLog(log);
    }
}