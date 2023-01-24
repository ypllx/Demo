package com.example.demo.repository;

import com.example.demo.model.Car;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class MockPersonRepository implements PersonRepository {
    @Override
    public List<Person> findAll() {
        return List.of(
                new Person(1L, "Sem", List.of(
                        new Car(101L, "Audi"),
                        new Car(102L, "Volvo"),
                        new Car(103L, "BMW")
                )),
                new Person(2L, "Mike", List.of(
                        new Car(104L, "Nissan")
                )),
                new Person(3L, "Richard", Collections.emptyList())
        );
    }
}
