package ru.job4j.io;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AnalyzeTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void unavailableOnePeriod() throws IOException {
        File source = tempFolder.newFile("unavailable.csv");
        File target = tempFolder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("200 11:02:02");
        }
        Analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01"));
    }

    @Test
    public void unavailableTwoPeriods() throws IOException {
        File source = tempFolder.newFile("unavailable.csv");
        File target = tempFolder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:01");
            out.println("200 11:02:02");
        }
        Analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> list;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            list = in.lines().toList();
        }
        assertThat(list.toString(), is("[10:57:01;10:59:01, 11:01:01;11:02:02]"));
    }

    @Test
    public void unavailableThreePeriods() throws IOException {
        File source = tempFolder.newFile("unavailable.csv");
        File target = tempFolder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:01");
            out.println("200 11:02:02");
            out.println("200 11:09:01");
            out.println("400 11:11:01");
            out.println("200 11:12:02");
        }
        Analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> list;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            list = in.lines().toList();
        }
        assertThat(list.toString(), is("[10:57:01;10:59:01, 11:01:01;11:02:02, 11:11:01;11:12:02]"));
    }

    @Test
    public void unavailableWhenGarbage() throws IOException {
        File source = tempFolder.newFile("unavailable.csv");
        File target = tempFolder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("Error!");
        }
        Analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> list;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            list = in.lines().toList();
        }
        assertTrue(list.isEmpty());
    }

    @Test
    public void unavailableWhenFileNotFound() throws IOException {
        File source = tempFolder.newFile("unavailable.csv");
        File target = tempFolder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            //nothing to log
        }
        Analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> list;
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            list = in.lines().toList();
        }
        assertTrue(list.isEmpty());
    }
}