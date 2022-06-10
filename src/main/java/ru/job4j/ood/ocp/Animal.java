package ru.job4j.ood.ocp;

import java.util.List;

public class Animal {

    List<String> animals = List.of("Cat", "Dog");

    String sound = "Unkown";

    String getSound(String animal) {
        if (animal.equals(animals.get(0))) {
            return "Meow";
        } else if (animal.equals(animals.get(1))) {
            return "Bark";
        }
        return this.sound;
    }

    String isHappy (boolean bool) {
        if (bool) {
            return "Wags the tail";
        }
        return "Does not wag the tail";
    }

    public static void main(String[] args) {
        List<String> animals = new Animal().animals;
        System.out.println(animals);
        try {animals.add("Frog");
            System.out.println(animals);
        } catch (UnsupportedOperationException exception) {
            System.out.println("No more animals can be added. Sorry!");
        }
    }
}

/*
* Нарушения принципа OCP (например, если понадобится создать класс "Лягушка":
*
* 1. Не используется абстракция при создании класса - лучше сделать класс
* Animal абстрактным, а реализации - в зависимости от типа животных.
*
* 2. Immutable поле класса List<String> animals потребует изменения при соаздании Лягушки,
* а принцип OCP гласит, что "должна быть возможно расширения, но не изменения".
*
* 3. Метод getSound будет изменен при добавлении Лягушки - это нарушение принципа.
*
* 4. Метод isHappy для Лягушки потребует 3-го состояния (нет у нее эмоций), поэтому
* придется менять тип параметра метода, а также тип возвращаемого методом значения
* (не зависящим от наличия хвоста и умения им вилять).
* */