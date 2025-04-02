package com.dav.labs.cours_service.courses.dto.responses;

import com.dav.labs.cours_service.clients.models.AcademicYear;
import com.dav.labs.cours_service.clients.models.Classe;
import com.dav.labs.cours_service.courses.entities.CourseType;
import com.dav.labs.cours_service.subjects.dto.responses.SubjectDtoResponse;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseDtoResponse implements Serializable {
    private String id;
    private String subjectId;
    private SubjectDtoResponse subject;
    private String classeId;
    private Classe classe;
    private String academicYearId;
    private AcademicYear academicYear;
    private CourseType courseType;
    private int coefficient;
    private Boolean archive;
}
