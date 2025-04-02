package com.dav.labs.users_service.registrations.dto.requests;

import com.dav.labs.users_service.registrations.entities.RegisterStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDtoRequest implements Serializable {
    @NotBlank(message = "Le champ Etudiant est requis!")
    private String studentId;
    @NotBlank(message = "La classe est requise!")
    private String classeId;
    @NotBlank(message = "L'année academique requise!")
    private String academicYearId;

}
