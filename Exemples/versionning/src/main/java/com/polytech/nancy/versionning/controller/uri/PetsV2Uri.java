package com.polytech.nancy.versionning.controller.uri;

import com.polytech.nancy.versionning.domain.Pet;
import com.polytech.nancy.versionning.repository.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/uri/v2")
public class PetsV2Uri {

    @GetMapping("/pets")
    public List<Pet> getPets() throws ParseException {
        return PetRepository.getAllPetsV2();
    }

    @GetMapping("/pets/{petId}")
    public Pet getPet(@PathVariable Integer petId) throws ParseException {
        return PetRepository.getAllPetsV2().stream().filter(p -> p.getId() == petId).findFirst().orElse(null);
    }

}
