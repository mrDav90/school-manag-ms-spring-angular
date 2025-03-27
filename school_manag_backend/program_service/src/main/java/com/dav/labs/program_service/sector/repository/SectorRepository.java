package com.dav.labs.program_service.sector.repository;


import com.dav.labs.program_service.sector.entities.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, String> {
    Optional<SectorEntity> findByName(String name);
}
