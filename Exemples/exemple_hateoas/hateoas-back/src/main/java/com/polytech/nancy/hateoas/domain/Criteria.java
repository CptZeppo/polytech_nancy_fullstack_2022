package com.polytech.nancy.hateoas.domain;

import com.polytech.nancy.hateoas.domain.dto.CriteriaDto;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public class Criteria {
    private String theaterURI;
    private LocalDateTime startTime;

    public Criteria(String theaterURI, LocalDateTime startTime) {
        this.theaterURI = theaterURI;
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public UUID getTheaterID() {
        String id = theaterURI;
        if (theaterURI.contains("/")) {
            String[] splits = theaterURI.split("/");
            id = splits[splits.length -1];
        }
        return UUID.fromString(id);
    }

    public CriteriaDto toDTO() {
        return new CriteriaDto(URI.create(getTheaterID().toString()), startTime);
    }
}
