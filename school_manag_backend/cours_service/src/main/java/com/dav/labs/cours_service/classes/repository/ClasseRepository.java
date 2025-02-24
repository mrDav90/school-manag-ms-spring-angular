package com.dav.labs.cours_service.classes.repository;

import com.dav.labs.cours_service.classes.entities.ClasseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseEntity, Long> {
    Optional<ClasseEntity> findByName(String name);
}
