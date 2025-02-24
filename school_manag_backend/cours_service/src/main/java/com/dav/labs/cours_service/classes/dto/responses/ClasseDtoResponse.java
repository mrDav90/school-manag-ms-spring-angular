package com.dav.labs.cours_service.classes.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClasseDtoResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private boolean archive;
}
