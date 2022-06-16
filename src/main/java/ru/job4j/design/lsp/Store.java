package ru.job4j.design.lsp;

import java.util.List;

public interface Store {
    void putFood(Food food);
    List<Food> getFoodList();
}
