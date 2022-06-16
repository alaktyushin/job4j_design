package ru.job4j.design.lsp;

import java.util.Calendar;

public class ControlQuality {
    private final Store warehouse;
    private final Store shop;
    private final Store trash;

    final static double DISCOUNT = 0.5;

    public ControlQuality(Store warehouse, Store shop, Store trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void distribute(Food food) {
        if (food == null) {
            throw new IllegalArgumentException("Every food must be initialized.");
        }
        Calendar now = Calendar.getInstance();
        long e = food.getExpiryDate().getTimeInMillis() / 1000000;
        long c = food.getCreateDate().getTimeInMillis() / 1000000;
        long n = now.getTimeInMillis() / 1000000;
        if (n > e) {
            trash.putFood(food);
        } else if (n < ((e - c) * 0.25 + c)) {
            warehouse.putFood(food);
        } else if (n >= ((e - c) * 0.25 + c) && n < ((e - c) * 0.75 + c)) {
            shop.putFood(food);
        } else if (n >= ((e - c) * 0.75 + c)) {
            food.setDiscount(DISCOUNT);
            shop.putFood(food);
        }
    }
}
