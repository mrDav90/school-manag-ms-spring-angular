package com.dav.labs.cours_service.courses.entities;

import com.dav.labs.cours_service.clients.models.AcademicYear;
import com.dav.labs.cours_service.clients.models.Classe;
import com.dav.labs.cours_service.subjects.dto.responses.SubjectDtoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "courses")
public class CourseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String subjectId;

    @Transient
    private SubjectDtoResponse subject;

    @Column(nullable = false)
    private String classeId;

    @Transient
    private Classe classe;

    @Column(nullable = false)
    private String academicYearId;

    @Transient
    private AcademicYear academicYear;

    @Column(nullable = false)
    private CourseType courseType;

    @Column(nullable = false)
    private int coefficient;

    private Boolean archive;

}
