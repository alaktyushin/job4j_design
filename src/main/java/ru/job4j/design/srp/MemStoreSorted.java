package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStoreSorted implements Store {

    private final List<Employee> employees = new ArrayList<>();


    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees
                .stream()
                .filter(filter)
                .sorted(Comparator.comparing(Employee::getSalaryAmmount).reversed())
                .collect(Collectors.toList());
    }
}