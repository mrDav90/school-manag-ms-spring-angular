package com.dav.labs.program_service.classes.repository;


import com.dav.labs.program_service.classes.entities.ClasseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseEntity, String> {
    Optional<ClasseEntity> findByName(String name);
    Optional<ClasseEntity> findByNameAndSectorId(String name , String sectorId);
}
