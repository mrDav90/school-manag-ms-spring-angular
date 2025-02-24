package com.dav.labs.users_service.teachers.repository;

import com.dav.labs.users_service.teachers.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByEmailPro(String emailPro);
}
