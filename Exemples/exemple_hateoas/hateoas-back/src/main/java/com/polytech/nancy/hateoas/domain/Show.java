package com.polytech.nancy.hateoas.domain;

import com.polytech.nancy.hateoas.domain.dto.ShowDto;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Show {
    private final UUID id = UUID.randomUUID();
    private final Movie movie;
    private final Theater theater;
    private final int roomNumber;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Show(final Theater theater, final Movie movie, final LocalDateTime startTime, int roomNumber) {
        this.theater = theater;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration());
        this.roomNumber =roomNumber;
    }

    public static List<ShowDto> toListDTO(List<Show> shows) {
        if (shows == null) {
            return new ArrayList<>();
        }
        return shows.stream().map(Show::toDTO).toList();
    }

    public UUID getId() {
        return id;
    }

    public boolean hasCompatibleSchedule(Show lastSelectedShow) {
       return this.startTime.isAfter(lastSelectedShow.endTime.plusMinutes(5));
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public float getPrice() {
        return theater.getPrice();
    }

    public ShowDto toDTO() {
        return new ShowDto(id, movie.toDTO(), roomNumber, startTime, endTime, getPrice());
    }
}
