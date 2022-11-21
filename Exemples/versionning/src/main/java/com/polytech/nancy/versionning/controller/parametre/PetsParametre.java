package com.polytech.nancy.versionning.controller.parametre;

import com.polytech.nancy.versionning.domain.Pet;
import com.polytech.nancy.versionning.repository.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/parametre")
public class PetsParametre {

    @GetMapping(value = "/pets", params = "version=1")
    public List<Pet> getPetsV1() throws ParseException {
        return PetRepository.getAllPetsV1();
    }

    @GetMapping(value = "/pets/{petId}", params = "version=1")
    public Pet getPetV1(@PathVariable Integer petId) throws ParseException {
        return PetRepository.getAllPetsV1().stream().filter(p -> p.getId() == petId).findFirst().orElse(null);
    }

    @GetMapping(value = "/pets", params = "version=2")
    public List<Pet> getPetsV2() throws ParseException {
        return PetRepository.getAllPetsV2();
    }

    @GetMapping(value = "/pets/{petId}", params = "version=2")
    public Pet getPetV2(@PathVariable Integer petId) throws ParseException {
        return PetRepository.getAllPetsV2().stream().filter(p -> p.getId() == petId).findFirst().orElse(null);
    }
}
