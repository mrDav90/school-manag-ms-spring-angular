package com.dav.labs.program_service.sector.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SectorDtoResponse implements Serializable {
    private String id;
    private String name;
    private boolean archive;
}
