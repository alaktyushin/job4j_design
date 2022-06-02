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
        Salary salary = new SalarySimple(100.5);
        Employee worker = new Employee("Ivan", now, now,salary);
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
    public void whenSalaryTypeChangeAndOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Salary salary = new SalaryWithCurrencyType("USD",100);
        Employee worker = new Employee("Ivan", now, now,salary);
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
        Salary salary = new SalarySimple(100.5);
        Employee worker = new Employee("Ivan", now, now,salary);
        store.add(worker);
        Report engine = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<").append("Name").append(">")
                .append(System.lineSeparator())
                .append("<").append("Hired").append(">")
                .append(System.lineSeparator())
                .append("<").append("Fired").append(">")
                .append(System.lineSeparator())
                .append("<").append("Salary").append(">")
                .append(System.lineSeparator())
                .append("<").append(worker.getName()).append(">")
                .append(System.lineSeparator()).append("<")
                .append(worker.getHired()).append(">")
                .append(System.lineSeparator()).append("<")
                .append(worker.getFired()).append(">")
                .append(System.lineSeparator()).append("<")
                .append(worker.getSalary().toString()).append(">")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRreportGenerated() {
        MemStoreSorted store = new MemStoreSorted();
        Calendar now = Calendar.getInstance();

        Employee worker1 = new Employee("Ivan", now, now,new SalarySimple(100.5));
        store.add(worker1);
        Employee worker2 = new Employee("Andrey", now, now,new SalaryWithCurrencyType(400.5));
        store.add(worker2);
        Employee worker3 = new Employee("Petr", now, now,new SalarySimple(200.5));
        store.add(worker3);
        Employee worker4 = new Employee("Nina", now, now,new SalaryWithCurrencyType(300.5));
        store.add(worker4);

        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                    .append(worker2.getName()).append(";")
                    .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                    .append(worker4.getName()).append(";")
                    .append(worker4.getSalary()).append(";")
                .append(System.lineSeparator())
                    .append(worker3.getName()).append(";")
                    .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                    .append(worker1.getName()).append(";")
                    .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}