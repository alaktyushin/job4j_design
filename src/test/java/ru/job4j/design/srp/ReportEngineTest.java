package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("""
                        <!doctype html>
                        <html lang="ru">
                        <head>
                          <meta charset="utf-8">
                          <title>Job4j</title>
                        </head>
                        <body>
                        """)
                .append("  <").append("Name; Hired; Fired; Salary;").append(">")
                .append(System.lineSeparator())
                .append("  <").append(worker.getName()).append("; ")
                .append(worker.getHired()).append("; ")
                .append(worker.getFired()).append("; ")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>\n" +
                        "</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountantsSalaryConvertedToUSD() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100.25);
        store.add(worker);
        ReportEngineAccountants engine = new ReportEngineAccountants(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary in USD;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * engine.getConvertToUSD()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100.25);
        store.add(worker1);
        Employee worker2 = new Employee("Andrey", now, now, 200.0);
        store.add(worker2);
        Employee worker3 = new Employee("Petr", now, now, 400);
        store.add(worker3);
        Employee worker4 = new Employee("Nina", now, now, 100);
        store.add(worker4);
        Report engine = new ReportEngineHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker4.getName()).append(";")
                .append(worker4.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}