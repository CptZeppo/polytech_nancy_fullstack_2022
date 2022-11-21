package com.polytech.nancy.hateoas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "movies", itemRelation = "movie")
public class MovieDto {
    @JsonProperty
    private final UUID id;
    @JsonProperty
    private final String title;
    private final int durationInMinutes;
    @JsonProperty
    private final String posterURL;

    public MovieDto(UUID id, String title, int durationInMinutes, String posterURL) {
        this.id = id;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.posterURL = posterURL;
    }
}
