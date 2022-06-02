package ru.job4j.design.srp;

import java.util.Calendar;
import java.util.Objects;

public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private Salary salary;

    public Employee(String name, Calendar hired, Calendar fired, Salary salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public Salary getSalary() {
        return salary;
    }

    public double getSalaryAmmount() {
        Salary s = getSalary();
        return s.getAmmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}