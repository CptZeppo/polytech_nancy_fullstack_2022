package com.polytech.nancy.ETag.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Data
@AllArgsConstructor
public class WikiPage {

    final int id;
    Date lastModified;
    final String body;
}
