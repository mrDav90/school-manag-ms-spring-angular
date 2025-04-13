package com.dav.labs.users_service.students.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "students")
public class StudentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String placeOfBirth;

    private String emailPro;

    @Column(nullable = false)
    private String emailPerso;

    @Column(nullable = false)
    private String phoneNumber;

    private Address address;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private Gender gender;

    private boolean archive;

    @Column(nullable = false)
    private String registrationNu;

    private String profilePicture;

    private Document document;

    private String tutorFullName;
    private String tutorPhoneNumber;

}
