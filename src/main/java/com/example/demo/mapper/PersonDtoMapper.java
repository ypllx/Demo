package com.example.demo.mapper;

import com.example.demo.dto.CarDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.model.Car;
import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDtoMapper {
    private final CarDtoMapper carDtoMapper;

    public PersonDtoMapper(CarDtoMapper carDtoMapper) {
        this.carDtoMapper = carDtoMapper;
    }

    public PersonDto map(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());

        List<Car> cars = person.getCars();
        if (cars == null) {
            dto.setCars(null);
        } else {
            dto.setCars(cars.stream()
                    .map(carDtoMapper::map)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

}
