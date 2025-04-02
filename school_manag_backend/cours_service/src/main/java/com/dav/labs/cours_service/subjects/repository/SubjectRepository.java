package com.dav.labs.cours_service.subjects.repository;

import com.dav.labs.cours_service.subjects.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, String> {
    Optional<SubjectEntity> findByName(String name);
}
