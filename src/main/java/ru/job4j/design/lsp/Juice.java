package ru.job4j.design.lsp;

import java.util.Calendar;

public class Juice extends Food {
    public Juice(String name, Calendar createDate, Calendar expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
