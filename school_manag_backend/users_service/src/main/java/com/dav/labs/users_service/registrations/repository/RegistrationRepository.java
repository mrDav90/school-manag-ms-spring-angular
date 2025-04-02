package com.dav.labs.users_service.registrations.repository;

import com.dav.labs.users_service.registrations.entities.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, String> {
    Optional<RegistrationEntity> findByStudentIdAndAcademicYearId(String studentId, String academicYearId);
}
