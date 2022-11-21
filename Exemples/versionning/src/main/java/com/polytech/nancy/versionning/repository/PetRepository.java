package com.polytech.nancy.versionning.repository;

import com.polytech.nancy.versionning.domain.*;
import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PetRepository {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public List<Pet> getAllPetsV1() throws ParseException {
        List<Pet> pets = new ArrayList<>();
        pets.add(new PetV1(123, "Mochi", Species.CAT, Sex.MALE, dateFormat.parse("10/09/2020"), "Théo", "HENRY"));
        pets.add(new PetV1(999, "Padmé", Species.DOG, Sex.FEMALE, dateFormat.parse("05/03/2018"), "Jean", "MICHEL"));
        return pets;
    }

    public List<Pet> getAllPetsV2() throws ParseException {
        List<Pet> pets = new ArrayList<>();

        pets.add(new PetV2(123, "Mochi", Species.CAT, Sex.MALE, dateFormat.parse("10/09/2020"), new Owner("Théo", "HENRY")));
        pets.add(new PetV2(999, "Padmé", Species.DOG, Sex.FEMALE, dateFormat.parse("05/03/2018"), new Owner("Jean", "MICHEL")));
        return pets;
    }
}
