package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private Long id;
    private String name;

    private List<CarDto> cars;
}
