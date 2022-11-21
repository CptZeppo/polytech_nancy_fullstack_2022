package com.polytech.nancy.hateoas.repository;

import com.polytech.nancy.hateoas.domain.Search;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SearchRepository {
    Map<UUID, Search> searchs = new HashMap<>();

    public Search save(Search search) {
        searchs.put(search.getId(), search);
       return search;
    }

    public Search findById(UUID id) {
        return searchs.get(id);
    }
}
