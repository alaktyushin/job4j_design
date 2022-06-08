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
        text.append("!DOCTYPE HTML")
                .append(System.lineSeparator())
                .append("<html lang=\"ru\">")
                .append(System.lineSeparator())
                .append("<head>")
                .append(System.lineSeparator())
                .append("<meta charset=\"utf-8>")
                .append(System.lineSeparator())
                .append("<title>Job4j</title>")
                .append(System.lineSeparator())
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("  <").append("Name; Hired; Fired; Salary;").append(">")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("  <").append(employee.getName()).append("; ")
                    .append(employee.getHired()).append("; ")
                    .append(employee.getFired()).append("; ")
                    .append(employee.getSalary()).append("; ")
                    .append(System.lineSeparator());
        }
        text.append("</body>")
                .append(System.lineSeparator())
                .append("</html>")
                .append(System.lineSeparator());
        return text.toString();
    }
}