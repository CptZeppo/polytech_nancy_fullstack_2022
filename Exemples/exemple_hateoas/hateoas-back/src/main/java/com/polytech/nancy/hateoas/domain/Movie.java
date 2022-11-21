package com.polytech.nancy.hateoas.domain;

import com.polytech.nancy.hateoas.domain.dto.MovieDto;

import java.util.UUID;

public class Movie {
    private final UUID id = UUID.randomUUID();
    private final String title;
    private final int durationInMinutes;
    private final String posterURL;

    public Movie(String title, int durationInMinutes, String posterURL) {
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.posterURL = posterURL;
    }

    public UUID getId() {
        return id;
    }

    public long getDuration() {
       return this.durationInMinutes;
    }

    public MovieDto toDTO() {
        return new MovieDto(id, title, durationInMinutes,posterURL);
    }
}
