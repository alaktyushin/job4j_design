package ru.job4j.design.srp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DateAdapterJson extends JsonSerializer<Employee> {

    private static final SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", employee.getName());
        jsonGenerator.writeStringField("hired", formatter.format(employee.getHired().getTime()));
        jsonGenerator.writeStringField("fired", formatter.format(employee.getFired().getTime()));
        jsonGenerator.writeNumberField("salary", employee.getSalary());
        jsonGenerator.writeEndObject();
    }
}
