package com.polytech.nancy.hateoas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.polytech.nancy.hateoas.domain.Theater;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "theaters", itemRelation = "theater")
public class TheaterDto extends RepresentationModel<TheaterDto> {
    @JsonProperty
    private final UUID id;
    @JsonProperty
    private final String name;

    public TheaterDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

}
