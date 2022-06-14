package ru.job4j.design.srp;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
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
        try {
            context = JAXBContext.newInstance(Employee.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        Marshaller marshaller;
        try {
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        }
        StringWriter writer = new StringWriter();
        for (Employee employee : store.findBy(filter)) {
            try {
                marshaller.marshal(employee, writer);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return writer.toString();
    }
}