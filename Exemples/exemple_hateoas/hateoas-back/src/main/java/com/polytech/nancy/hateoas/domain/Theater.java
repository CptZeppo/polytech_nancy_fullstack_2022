package com.polytech.nancy.hateoas.domain;

import com.polytech.nancy.hateoas.domain.dto.TheaterDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Theater {
    private final UUID id= UUID.randomUUID();
    private final int numberOfRooms;
    private final String name;
    private  final float price;

    public Theater(String name, float price) {
        this.name = name;
        this.numberOfRooms = new Random().nextInt(1,3);
        this.price = price;
    }

    public static List<TheaterDto> toListDTO(List<Theater> allTheater) {
        if (allTheater == null) {
            return new ArrayList<>();
        }
        return allTheater.stream().map(Theater::toDTO).toList();
    }

    public UUID getId() {
        return id;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public float getPrice() {
        return price;
    }

    public TheaterDto toDTO() {
        return new TheaterDto(id, name);
    }
}
