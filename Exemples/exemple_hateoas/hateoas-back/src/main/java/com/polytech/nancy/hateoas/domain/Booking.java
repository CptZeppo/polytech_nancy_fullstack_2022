package com.polytech.nancy.hateoas.domain;

import com.polytech.nancy.hateoas.domain.dto.BookingDto;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Booking  {
    private final UUID id= UUID.randomUUID();;
    private final List<Show> shows;
    private final BigDecimal totalPrice;

    public Booking(List<Show> shows, BigDecimal totalPrice) {
        this.shows = shows;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public BookingDto toDto() {

        return new BookingDto(id, Show.toListDTO(shows), totalPrice);
    }
}
