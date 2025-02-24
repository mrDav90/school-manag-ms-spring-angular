package com.dav.labs.users_service.teachers.entities;

import com.dav.labs.users_service.clients.models.TeachingAssignment;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "teachers")
public class TeacherEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String emailPro;

    @Column(nullable = false)
    private String phoneNumber;

    private String address;

    private boolean archive;

    private String registrationNu;

    @Transient
    private List<TeachingAssignment> teachingAssignments;

}
