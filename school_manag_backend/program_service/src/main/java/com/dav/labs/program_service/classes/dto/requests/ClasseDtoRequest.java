package com.dav.labs.program_service.classes.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDtoRequest implements Serializable {
    @NotBlank(message = "Name is required!")
    private String name;
    @NotNull(message = "Sector Id is required!")
    private String sectorId;
}
