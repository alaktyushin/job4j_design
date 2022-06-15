package ru.job4j.design.srp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngineJSON implements Report {

    private Store store;

    public ReportEngineJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        StringBuilder text = new StringBuilder();
        List<Employee> employees = store.findBy(filter);
        module.addSerializer(Employee.class, new DateAdapterJson());
        mapper.registerModule(module);
        try {
            text.append(mapper.writeValueAsString(employees.listIterator()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return text.toString();

    }
}