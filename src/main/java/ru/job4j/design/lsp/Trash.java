package ru.job4j.design.lsp;

import java.util.LinkedList;
import java.util.List;

public class Trash implements Store {
    List<Food> foodList = new LinkedList<>();
    @Override
    public void putFood(Food food) {
        foodList.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }
}
