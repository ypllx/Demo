package com.example.demo.mapper;

import com.example.demo.dto.CarDto;
import com.example.demo.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarDtoMapper {

    CarDto map(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setName(car.getName());
        return dto;
    }
}
