package com.polytech.nancy.hateoas.domain;

import com.polytech.nancy.hateoas.domain.dto.SearchDto;
import com.polytech.nancy.hateoas.domain.dto.ShowDto;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Search {
    private final UUID id = UUID.randomUUID();
    private final Criteria criteria;
    private final Map<UUID, Show> searchResult = new HashMap<>();
    private final Selection selection = new Selection();

    public Search(Criteria criteria, List<Show> shows) {
        this.criteria = criteria;
        shows.forEach(show -> searchResult.put(show.getId(), show));
    }

    public UUID getId() {
        return id;
    }

    public List<Show> getSearchResult() {
        return List.copyOf(searchResult.values());
    }

    public List<Show> getSelectableShow(Integer movieIndex) {
        if (selection.isEmpty() || movieIndex <= 0) {
            return getSearchResult();
        }

        Show lastSelectedShow = selection.getByMovieIndex(movieIndex - 1);

        return searchResult.values().stream().filter(show -> show.hasCompatibleSchedule(lastSelectedShow)).toList();
    }

    public Selection getSelection() {
        return this.selection;
    }

    public Selection addSelection(UUID showID, Boolean resetSelection) {
        Show selectedShow = searchResult.get(showID);
        if (resetSelection) {
            selection.reset();
        }
        return selection.addShow(selectedShow);
    }

    public SearchDto toDTO() {
        Map<UUID, ShowDto> showDtoMap = new HashMap<>();
        searchResult.values().forEach(show -> showDtoMap.put(show.getId(), show.toDTO()));
        return new SearchDto(id, criteria.toDTO(), showDtoMap, selection.toDTO());
    }
}
