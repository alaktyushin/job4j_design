package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static int[][] multiple(int size, String name) {
        int[][] data = new int[0][];
        try (FileOutputStream out = new FileOutputStream(name)) {
            data = new int[size][size];
            for (int row = 0; row < data.length; row++) {
                for (int cell = 0; cell < data[row].length; cell++) {
                    data[row][cell] = (row + 1) * (cell + 1);
                    out.write(String.valueOf(data[row][cell]).getBytes());
                    out.write(" \t".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        Matrix.multiple(10, "matrix.txt");
    }
}