package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Employee worker2 = new Employee("Petr", now, now, 400);
        store.add(worker2);
        Report engine = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("!DOCTYPE HTML")
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
                .append(System.lineSeparator())
                .append("  <").append(worker1.getName()).append("; ")
                .append(worker1.getHired()).append("; ")
                .append(worker1.getFired()).append("; ")
                .append(worker1.getSalary()).append("; ")
                .append(System.lineSeparator())
                .append("  <").append(worker2.getName()).append("; ")
                .append(worker2.getHired()).append("; ")
                .append(worker2.getFired()).append("; ")
                .append(worker2.getSalary()).append("; ")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountantsSalaryConvertedToUSD() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100.25);
        store.add(worker);
        Report engine = new ReportEngineAccountants(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary in USD;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / ReportEngineAccountants.CONVERT_TO_USD).append(";")
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

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Employee worker1 = new Employee("Ivan", now, now, 100.25);
        store.add(worker1);
        Employee worker2 = new Employee("Andrey", now, now, 200.0);
        store.add(worker2);
        Employee worker3 = new Employee("Petr", now, now, 400);
        store.add(worker3);
        Report engine = new ReportEngineXML(store);
        StringWriter expect = new StringWriter()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(System.lineSeparator())
                .append("<employees>")
                .append(System.lineSeparator())
                .append("    <employee>")
                .append(System.lineSeparator())
                .append("        <name>")
                .append(worker1.getName())
                .append("</name>")
                .append(System.lineSeparator())
                .append("        <hired>")
                .append(formatter.format(worker1.getHired().getTime()))
                .append("</hired>")
                .append(System.lineSeparator())
                .append("        <fired>")
                .append(formatter.format(worker1.getFired().getTime()))
                .append("</fired>")
                .append(System.lineSeparator())
                .append("        <salary>")
                .append(String.valueOf(worker1.getSalary()))
                .append("</salary>")
                .append(System.lineSeparator())
                .append("    </employee>")
                .append(System.lineSeparator())
                .append("    <employee>")
                .append(System.lineSeparator())
                .append("        <name>")
                .append(worker2.getName())
                .append("</name>")
                .append(System.lineSeparator())
                .append("        <hired>")
                .append(formatter.format(worker2.getHired().getTime()))
                .append("</hired>")
                .append(System.lineSeparator())
                .append("        <fired>")
                .append(formatter.format(worker2.getFired().getTime()))
                .append("</fired>")
                .append(System.lineSeparator())
                .append("        <salary>")
                .append(String.valueOf(worker2.getSalary()))
                .append("</salary>")
                .append(System.lineSeparator())
                .append("    </employee>")
                .append(System.lineSeparator())
                .append("    <employee>")
                .append(System.lineSeparator())
                .append("        <name>")
                .append(worker3.getName())
                .append("</name>")
                .append(System.lineSeparator())
                .append("        <hired>")
                .append(formatter.format(worker3.getHired().getTime()))
                .append("</hired>")
                .append(System.lineSeparator())
                .append("        <fired>")
                .append(formatter.format(worker3.getFired().getTime()))
                .append("</fired>")
                .append(System.lineSeparator())
                .append("        <salary>")
                .append(String.valueOf(worker3.getSalary()))
                .append("</salary>")
                .append(System.lineSeparator())
                .append("    </employee>")
                .append(System.lineSeparator())
                .append("</employees>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Employee worker1 = new Employee("Ivan", now, now, 100.25);
        store.add(worker1);
        Employee worker2 = new Employee("Andrey", now, now, 200.0);
        store.add(worker2);
        Employee worker3 = new Employee("Petr", now, now, 400);
        store.add(worker3);
        Report engine = new ReportEngineJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"")
                .append(worker1.getName())
                .append("\",")
                .append("\"hired\":\"")
                .append(formatter.format(worker1.getHired().getTime()))
                .append("\",")
                .append("\"fired\":\"")
                .append(formatter.format(worker1.getFired().getTime()))
                .append("\",")
                .append("\"salary\":")
                .append(worker1.getSalary())
                .append("},")
                .append("{\"name\":\"")
                .append(worker2.getName())
                .append("\",")
                .append("\"hired\":\"")
                .append(formatter.format(worker2.getHired().getTime()))
                .append("\",")
                .append("\"fired\":\"")
                .append(formatter.format(worker2.getFired().getTime()))
                .append("\",")
                .append("\"salary\":")
                .append(worker2.getSalary())
                .append("},")
                .append("{\"name\":\"")
                .append(worker3.getName())
                .append("\",")
                .append("\"hired\":\"")
                .append(formatter.format(worker3.getHired().getTime()))
                .append("\",")
                .append("\"fired\":\"")
                .append(formatter.format(worker3.getFired().getTime()))
                .append("\",")
                .append("\"salary\":")
                .append(worker3.getSalary())
                .append("}]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}