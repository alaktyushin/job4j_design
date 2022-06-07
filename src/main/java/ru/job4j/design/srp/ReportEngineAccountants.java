package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportEngineAccountants implements Report {

    private final double convertToUSD = 1 / 87.03;

    private Store store;

    public ReportEngineAccountants(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary in USD;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * convertToUSD).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public double getConvertToUSD() {
        return convertToUSD;
    }
}