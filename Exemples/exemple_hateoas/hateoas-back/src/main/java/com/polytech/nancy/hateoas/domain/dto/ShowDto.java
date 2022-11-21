package com.polytech.nancy.hateoas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.UUID;

@Relation(collectionRelation = "shows", itemRelation = "show")
public class ShowDto extends RepresentationModel<ShowDto> {
    @JsonProperty
    private final UUID id;
    @JsonProperty
    private final MovieDto movie;
    private final int roomNumber;
    @JsonProperty
    private final LocalDateTime startTime;
    @JsonProperty
    private final LocalDateTime endTime;
    @JsonProperty
    private final float price;

    public ShowDto(UUID id, MovieDto movie, int roomNumber, LocalDateTime startTime, LocalDateTime endTime, float price) {
        this.id = id;
        this.movie = movie;
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }
}
