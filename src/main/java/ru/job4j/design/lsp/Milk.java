package ru.job4j.design.lsp;

import java.util.Calendar;

public class Milk extends Food {
    public Milk(String name, Calendar createDate, Calendar expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
