package com.polytech.nancy.ETag.controller;

import com.polytech.nancy.ETag.repository.WikiPageRepository;
import com.polytech.nancy.ETag.domain.WikiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/wikis")
public class ETag {

    @Autowired
    private WikiPageRepository repository;

    @GetMapping
    public List<WikiPage> all() {
        return repository.all();
    }

    @PostMapping()
    public WikiPage add(@RequestBody String body) throws Exception {
       return repository.addNewPage(body);
    }

    @GetMapping(value = "/{id}")
    public WikiPage find(@PathVariable("id") final int id) {
        return repository.find(id);
    }

    @PutMapping(value = "/{id}")
    public WikiPage edit(@PathVariable("id") final int id, @RequestBody String body) {
        return repository.editPage(id, body);
    }

}
