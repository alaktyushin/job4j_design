package ru.job4j.design.srp;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportEngineXML implements Report {

    private Store store;

    public ReportEngineXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        JAXBContext context;
        StringWriter writer = new StringWriter();
        Employees employees = new Employees(store.findBy(filter));
        Marshaller marshaller;
        try {
            context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employees, writer);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }
}