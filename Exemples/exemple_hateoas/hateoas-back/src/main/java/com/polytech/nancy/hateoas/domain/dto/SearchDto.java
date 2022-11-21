package com.polytech.nancy.hateoas.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Relation(collectionRelation = "searches", itemRelation = "search")
public class SearchDto extends RepresentationModel<SearchDto> {
    @JsonProperty
    private final UUID id;
    private final CriteriaDto criteria;
    private final Map<UUID, ShowDto> searchResult;
    private final SelectionDto selection;

    public SearchDto(UUID id, CriteriaDto criteria, Map<UUID, ShowDto> searchResult, SelectionDto selection) {
        this.id = id;
        this.criteria = criteria;
        this.searchResult = searchResult;
        this.selection = selection;
    }

    public UUID getId() {
        return id;
    }
}
