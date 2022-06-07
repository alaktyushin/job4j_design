package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportHTML implements Report {

    private Store store;

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("""
                <!doctype html>
                <html lang="ru">
                <head>
                  <meta charset="utf-8">
                  <title>Job4j</title>
                </head>
                <body>
                """);
        text.append("  <").append("Name; Hired; Fired; Salary;").append(">")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("  <").append(employee.getName()).append("; ")
                    .append(employee.getHired()).append("; ")
                    .append(employee.getFired()).append("; ")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator())
                    .append("</body>\n" +
                            "</html>");
        }
        return text.toString();
    }
}