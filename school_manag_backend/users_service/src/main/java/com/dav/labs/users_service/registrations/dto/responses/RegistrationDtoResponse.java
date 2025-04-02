package com.dav.labs.users_service.registrations.dto.responses;

import com.dav.labs.users_service.clients.feign.models.AcademicYearDto;
import com.dav.labs.users_service.clients.feign.models.ClasseDto;
import com.dav.labs.users_service.registrations.entities.RegisterStatus;
import com.dav.labs.users_service.students.dto.responses.StudentDtoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationDtoResponse implements Serializable {
    private String id;
    private String registrationNu;
    private LocalDateTime registrationDate;
    private String studentId;
    private StudentDtoResponse student;
    private String academicYearId;
    private AcademicYearDto academicYear;
    private String classeId;
    private ClasseDto classe;
    private RegisterStatus status;
}
