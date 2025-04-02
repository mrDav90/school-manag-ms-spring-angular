package com.dav.labs.cours_service.courses.dto.requests;

import com.dav.labs.cours_service.courses.entities.CourseType;
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
public class CourseDtoRequest implements Serializable {

    @NotBlank(message = "Subject is required!")
    private String subjectId;

    @NotBlank(message = "Classe is required!")
    private String classeId;

    @NotBlank(message = "Academic year is required!")
    private String academicYearId;

    @NotNull(message = "Course type is required!")
    private CourseType courseType;

    @NotNull(message = "Coefficient is required!")
    private int coefficient;
}
