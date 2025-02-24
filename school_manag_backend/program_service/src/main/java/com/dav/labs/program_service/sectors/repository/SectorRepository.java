package com.dav.labs.program_service.sectors.repository;


import com.dav.labs.program_service.sectors.entities.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Long> {
    Optional<SectorEntity> findByName(String name);
}
