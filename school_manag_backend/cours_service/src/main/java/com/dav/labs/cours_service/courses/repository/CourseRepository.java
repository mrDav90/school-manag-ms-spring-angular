package com.dav.labs.cours_service.courses.repository;

import com.dav.labs.cours_service.courses.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    Optional<CourseEntity> findBySubjectIdAndClasseIdAndAcademicYearId(String subjectId, String classeId, String academicYearId);
}
