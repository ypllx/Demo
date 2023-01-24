package com.example.demo.controller;

import com.example.demo.mapper.CarDtoMapper;
import com.example.demo.mapper.PersonDtoMapper;
import com.example.demo.model.Car;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@SpringBootTest(classes = {PersonDtoMapper.class, CarDtoMapper.class, PersonController.class})
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    @Test
    void givenFewPersons_findAll_shouldReturnListOfPersonsWithCars() throws Exception {
        when(personService.findAll()).thenReturn(generateListPerson());

        String expectedJson = getContentFromFile("web/personList.json");

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void givenEmptyList_findAll_shouldReturnEmptyList() throws Exception {
        when(personService.findAll()).thenReturn(generateEmptyList());
        mockMvc.perform(get("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

    }

    private String getContentFromFile(String path) throws IOException {
        return new String(
                getClass().getClassLoader()
                        .getResourceAsStream(path)
                        .readAllBytes());
    }


    public static List<Person> generateListPerson() {
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

    public static List<Person> generateEmptyList() {
        return Collections.emptyList();
    }
}
