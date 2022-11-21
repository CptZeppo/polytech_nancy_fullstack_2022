package com.polytech.nancy.hateoas.repository;

import com.polytech.nancy.hateoas.domain.Theater;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class TheaterRepository {

    Map<UUID, Theater> theaters = new HashMap<>();

    @PostConstruct
    public void init() {
        List.of(
                new Theater("UGC Nancy Saint Jean", 12.5f),
                new Theater("Kinepolis", 12.8f),
                new Theater("Caméo Saint-Sébastien",8f),
                new Theater("Caméo Commanderie", 8f)
        ).forEach(theater -> theaters.put(theater.getId(), theater));
    }

    public List<Theater> getAll() {
        return List.copyOf(theaters.values());
    }

    public Theater findById(UUID id) {
        return theaters.get(id);
    }
}
