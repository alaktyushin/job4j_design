package ru.job4j.design.srp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
        module.addSerializer(Employee.class, new DateAdapterJson());
        mapper.registerModule(module);
        for (Employee employee : store.findBy(filter)) {
            try {
                text.append(mapper.writeValueAsString(employee));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return text.toString();

    }
}