package com.dav.labs.users_service.registrations.entities;

import com.dav.labs.users_service.clients.feign.models.AcademicYearDto;
import com.dav.labs.users_service.clients.feign.models.ClasseDto;
import com.dav.labs.users_service.students.dto.responses.StudentDtoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "registrations")
public class RegistrationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String registrationNu;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private String studentId;

    @Transient
    @Column(nullable = false)
    private StudentDtoResponse student;

    @Column(nullable = false)
    private String academicYearId;

    @Transient
    @Column(nullable = false)
    private AcademicYearDto academicYear;

    @Column(nullable = false)
    private String classeId;

    @Transient
    @Column(nullable = false)
    private ClasseDto classe;

    @Column(nullable = false)
    private RegisterStatus status;
}
