package ru.job4j.design.srp;

public class SalarySimple extends Salary {

    double ammount;

    public SalarySimple(double ammount) {
        this.ammount = ammount;
    }

    public double getAmmount() {
        return ammount;
    }

    @Override
    public String toString() {
        return String.valueOf(ammount);
    }
}
