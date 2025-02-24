package com.dav.labs.cours_service.teaching_assignments.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "teaching_assignments")
public class TeachingAssignmentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long teacherId;
    @Transient
    private String teacherName;
    @Column(nullable = false)
    private Long courseId;
    @Transient
    private String courseName;
    @Column(nullable = false)
    private Long classeId;
    @Transient
    private String classeName;
}
