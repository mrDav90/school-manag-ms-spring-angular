package com.dav.labs.program_service.niveau.dto.responses;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NiveauDtoResponse implements Serializable {
    private String id;
    private String name;
    private String sectorId;
    private String sectorName;
    private boolean archive;
}
