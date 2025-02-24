package com.dav.labs.cours_service.teaching_assignments.dto.requests;

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
public class TeachingAssignmentDtoRequest implements Serializable {
    @NotNull(message = "Teacher id is required!")
    private Long teacherId;

    @NotNull(message = "Course id is required!")
    private Long courseId;

    @NotNull(message = "Classe id is required!")
    private Long classeId;
}
