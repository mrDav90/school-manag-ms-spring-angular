package com.dav.labs.program_service.academic_year.repository;

import com.dav.labs.program_service.academic_year.entities.AcademicYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYearEntity , String> {
    public Optional<AcademicYearEntity> findByLabel(String label);
}
