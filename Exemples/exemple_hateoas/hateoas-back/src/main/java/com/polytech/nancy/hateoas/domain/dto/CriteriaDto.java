package com.polytech.nancy.hateoas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polytech.nancy.hateoas.domain.Criteria;
import org.springframework.hateoas.server.core.Relation;

import java.net.URI;
import java.time.LocalDateTime;

@Relation(collectionRelation = "criterias", itemRelation = "criteria")
public class CriteriaDto {
    @JsonProperty
    private final URI theaterURI;
    @JsonProperty
    private final LocalDateTime startTime;

    public CriteriaDto() {
        this.theaterURI = null;
        this.startTime = null;
    }

    public CriteriaDto(URI theaterURI, LocalDateTime startTime) {
        this.theaterURI = theaterURI;
        this.startTime = startTime;
    }

    public Criteria toDomaine() {
        return new Criteria(theaterURI.toString(), startTime);
    }
}
