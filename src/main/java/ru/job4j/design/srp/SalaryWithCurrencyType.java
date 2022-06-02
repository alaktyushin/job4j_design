package ru.job4j.design.srp;

public class SalaryWithCurrencyType extends Salary {

    String type;
    double ammount;

    public SalaryWithCurrencyType(String type, double ammount) {
        this.type = type;
        this.ammount = ammount;
    }

    public SalaryWithCurrencyType(double ammount) {
        this.type = "RUR";
        this.ammount = ammount;
    }

    public double getAmmount() {
        return ammount;
    }

    @Override
    public String toString() {
        return "type='" + type + '\'' +
                ", ammount=" + ammount;
    }
}
