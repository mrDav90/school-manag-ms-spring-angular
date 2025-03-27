package com.dav.labs.program_service.niveau.dto.requests;

import jakarta.persistence.Column;
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
public class NiveauDtoRequest implements Serializable {
    @NotBlank(message = "Name is required!")
    private String name;
    @NotNull(message = "Sector Id is required!")
    private String sectorId;
}
