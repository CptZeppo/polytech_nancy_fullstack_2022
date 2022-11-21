package com.polytech.nancy.ETag.repository;

import com.polytech.nancy.ETag.domain.WikiPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WikiPageRepository {

    private List<WikiPage> pages = init();

    private static List<WikiPage> init() {
        List<WikiPage> pages = new ArrayList<>();
        pages.add(new WikiPage(1, new Date(), "Je suis une page de wiki"));
        return pages;
    }

    public WikiPage addNewPage(String body) {
        int id = pages.stream().mapToInt(WikiPage::getId).max().orElseThrow();
        return addPage(id +1, body);
    }

    public WikiPage editPage(int id, String body) {
        pages.stream().filter(p -> p.getId() == id).findFirst().ifPresent(oldPage -> pages.remove(oldPage));
        return addPage(id, body);
    }

    public WikiPage find(int id) {
        return pages.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(this::notFoundException);
    }

    private ResponseStatusException notFoundException() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }

    public List<WikiPage> all() {
        return pages;
    }

    private WikiPage addPage(int id, String body) {
        WikiPage page = new WikiPage(id, new Date(), body);
        pages.add(page);
        return page;
    }
}
