package com.polytech.nancy.versionning.domain;

import lombok.Value;

import java.util.Date;

@Value
public class PetV1 implements Pet {

    int id;
    String name;
    Species species;
    Sex sex;
    Date birthday;
    String ownerFirstName;
    String ownerLastName;
}
