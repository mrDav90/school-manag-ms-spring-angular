package com.dav.labs.program_service.classes.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClasseDtoResponse implements Serializable {
    private String id;
    private String name;
    private String sectorId;
    private String sectorName;
    private boolean archive;
}
