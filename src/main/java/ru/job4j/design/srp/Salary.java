package ru.job4j.design.srp;

public abstract class Salary implements Comparable<Salary> {

    abstract double getAmmount();

    @Override
    public int compareTo(Salary anotherSalary) {
        return anotherSalary.compareTo(this);
    }
}
