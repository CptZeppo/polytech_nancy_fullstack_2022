package com.polytech.nancy.versionning.controller.header;

import com.polytech.nancy.versionning.domain.Pet;
import com.polytech.nancy.versionning.repository.PetRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MimeType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/header")
public class PetsHeader {

    @GetMapping("/pets")
    public List<Pet> getPets(@RequestHeader(HttpHeaders.ACCEPT) MimeType mimeType) throws ParseException {
        String version = mimeType.getParameter("version");
        if (StringUtils.hasLength(version) && "2".equals(version)) {
            return PetRepository.getAllPetsV2();
        }
        return PetRepository.getAllPetsV1();
    }

    @GetMapping(value = "/pets/{petId}")
    public Pet getPet(@PathVariable Integer petId, @RequestHeader(HttpHeaders.ACCEPT) MimeType mimeType) throws ParseException {

        String version = mimeType.getParameter("version");
        if (StringUtils.hasLength(version) && "2".equals(version)) {
            return PetRepository.getAllPetsV2().stream().filter(p -> p.getId() == petId).findFirst().orElse(null);
        }
        return PetRepository.getAllPetsV1().stream().filter(p -> p.getId() == petId).findFirst().orElse(null);
    }
}
