package com.polytech.nancy.versionning.controller.gererChangementMineurs;

import com.polytech.nancy.versionning.domain.PetDeserialisation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/gererChangementMineurs")
@Slf4j
public class Pets {

//    @PostMapping("/pets")
//    public String addPet(@RequestBody String jsonAsString) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        PetDeserialisation pet = mapper.readValue(jsonAsString, PetDeserialisation.class);
//        return "Ajout d'un nouvel animal a la base: \n" + pet;
//    }

    @PostMapping("/pets")
    public String addPet(@RequestBody PetDeserialisation pet) {
        return "Ajout d'un nouvel animal a la base: \n" + pet;
    }
}
