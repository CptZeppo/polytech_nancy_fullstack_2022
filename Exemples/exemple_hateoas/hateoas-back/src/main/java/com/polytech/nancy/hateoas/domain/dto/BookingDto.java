package com.polytech.nancy.hateoas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Relation(collectionRelation = "bookings", itemRelation = "booking")
public class BookingDto extends RepresentationModel<BookingDto>  {
    @JsonProperty
    private final UUID id;
    @JsonProperty
    private final List<ShowDto> shows;
    @JsonProperty
    private final BigDecimal totalPrice;

    public BookingDto(UUID id, List<ShowDto> shows, BigDecimal totalPrice) {
        this.id = id;
        this.shows = shows;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return id;
    }
}
