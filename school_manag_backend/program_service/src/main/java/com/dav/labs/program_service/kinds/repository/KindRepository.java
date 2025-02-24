package com.dav.labs.program_service.kinds.repository;

import com.dav.labs.program_service.kinds.entities.KindEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KindRepository extends JpaRepository<KindEntity, Long> {
    Optional<KindEntity> findByName(String name);
}
