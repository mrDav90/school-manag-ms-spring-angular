package com.dav.labs.program_service.niveau.repository;


import com.dav.labs.program_service.niveau.entities.NiveauEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NiveauRepository extends JpaRepository<NiveauEntity, String> {
    Optional<NiveauEntity> findByName(String name);
    Optional<NiveauEntity> findByNameAndSectorId(String name , String sectorId);
}
