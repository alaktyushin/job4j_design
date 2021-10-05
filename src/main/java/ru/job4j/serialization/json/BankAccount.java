package ru.job4j.serialization.json;


public class BankAccount {
    private final String number;
    private final char currency;
    private final boolean isActive;

    public BankAccount(String number, char currency, boolean isActive) {
        this.number = number;
        this.currency = currency;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "BankAccount{"
                + "number=" + number
                + ", currency=" + currency
                + ", isActive=" + isActive
                + '}';
    }
}