package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccount {
    private String number;
    private char currency;
    private boolean isActive;

    public BankAccount() {
    }

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
