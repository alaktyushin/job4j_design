package ru.job4j.design.lsp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Food {

    private String name;
    private Calendar createDate;
    private Calendar expiryDate;
    private double price;
    private double discount;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Food(String name, Calendar createDate, Calendar expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        if (createDate.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
            throw new IllegalArgumentException("CreateDate must be less or equal this moment of today.");
        }
        if (createDate.getTimeInMillis() > expiryDate.getTimeInMillis()) {
            throw new IllegalArgumentException("CreateDate must be less than expireDate.");
        }
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) ==
                0 && name.equals(food.name)
                && createDate.equals(food.createDate)
                && expiryDate.equals(food.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiryDate, price);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", createDate=" + formatter.format(createDate.getTime()) +
                ", expiryDate=" + formatter.format(expiryDate.getTime()) +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
