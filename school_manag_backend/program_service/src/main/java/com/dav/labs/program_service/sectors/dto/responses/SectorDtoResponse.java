package com.dav.labs.program_service.sectors.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SectorDtoResponse implements Serializable {
    private Long id;
    private String name;
    private boolean archive;
}
