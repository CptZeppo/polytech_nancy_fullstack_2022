package com.polytech.nancy.hateoas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Relation(collectionRelation = "selections", itemRelation = "selection")
public class SelectionDto extends RepresentationModel<SelectionDto> {
    @JsonProperty
    private final List<ShowDto> selectedShows;

    public SelectionDto(List<ShowDto> selectedShows, boolean complete) {
        this.selectedShows = selectedShows;
    }
}
