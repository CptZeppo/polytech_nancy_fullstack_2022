package com.polytech.nancy.hateoas.controler;

import com.polytech.nancy.hateoas.domain.Theater;
import com.polytech.nancy.hateoas.domain.dto.TheaterDto;
import com.polytech.nancy.hateoas.service.CinemasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/theaters")
public class TheaterController {

    @Autowired
    private CinemasterService service;


    @GetMapping
    public CollectionModel<TheaterDto> getAll() {
        List<TheaterDto> theaters = Theater.toListDTO(this.service.getAllTheater());
        theaters.forEach(this::addLinks);

        return CollectionModel.of(theaters,
                linkTo(methodOn(TheaterController.class).getAll()).withSelfRel(),
                linkTo(SearchController.class).withRel("search"));
    }

    @GetMapping("/{uuid}")
    public TheaterDto getByUUID(@PathVariable UUID uuid) {
        TheaterDto theater = this.service.findTheaterById(uuid).toDTO();
        return addLinks(theater);
    }

    private TheaterDto addLinks(TheaterDto theater) {
        theater.add(linkTo(methodOn(TheaterController.class).getByUUID(theater.getId())).withSelfRel());
        return theater;
    }

}
