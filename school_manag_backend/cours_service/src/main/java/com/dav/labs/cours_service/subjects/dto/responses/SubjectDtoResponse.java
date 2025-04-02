package com.dav.labs.cours_service.subjects.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubjectDtoResponse implements Serializable {
    private String id;
    private String name;
    private String description;
    private boolean archive;
}
