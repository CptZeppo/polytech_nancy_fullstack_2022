package com.polytech.nancy.versionning.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PetDeserialisation {

    int id;
    String name;
    Species species;
    Sex sex;
    Date birthday;
    String ownerFirstName;
    String ownerLastName;
}
