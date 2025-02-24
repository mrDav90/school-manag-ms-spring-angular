package com.dav.labs.cours_service.teaching_assignments.repository;

import com.dav.labs.cours_service.teaching_assignments.entities.TeachingAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignmentEntity, Long> {

}
