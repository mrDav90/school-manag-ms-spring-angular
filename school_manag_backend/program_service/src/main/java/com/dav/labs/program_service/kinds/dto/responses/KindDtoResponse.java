package com.dav.labs.program_service.kinds.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KindDtoResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private boolean archive;
}
