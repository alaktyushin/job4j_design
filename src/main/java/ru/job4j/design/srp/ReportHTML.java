package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportHTML implements Report {

    private Store store;

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<ru.job4j.design.srp.Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<").append("Name").append(">")
                .append(System.lineSeparator())
                .append("<").append("Hired").append(">")
                .append(System.lineSeparator())
                .append("<").append("Fired").append(">")
                .append(System.lineSeparator())
                .append("<").append("Salary").append(">")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<").append(employee.getName()).append(">")
                    .append(System.lineSeparator()).append("<")
                    .append(employee.getHired()).append(">")
                    .append(System.lineSeparator()).append("<")
                    .append(employee.getFired()).append(">")
                    .append(System.lineSeparator()).append("<")
                    .append(employee.getSalary().toString()).append(">")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}